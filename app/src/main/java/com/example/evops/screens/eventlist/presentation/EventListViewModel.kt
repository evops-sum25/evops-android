package com.example.evops.screens.eventlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.eventlist.domain.usecases.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(private val getEventsUseCase: GetEventsUseCase) :
    ViewModel() {
    private val _listState = MutableStateFlow(EventListState())
    val listState =
        _listState
            .onStart { processLoadFirstEvents() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = EventListState(),
            )

    fun onEvent(event: EventListEvent) {
        when (event) {
            is EventListEvent.LoadFirstEvents -> processLoadFirstEvents()
            is EventListEvent.LoadEvents -> processLoadEvents()
            is EventListEvent.SearchEvents -> {}
            is EventListEvent.UpdateSearchString -> {
                _listState.update { currentState ->
                    currentState.copy(searchString = event.searchString)
                }
            }
        }
    }

    private fun processLoadFirstEvents() {
        viewModelScope.launch {
            getEventsUseCase().collect { result ->
                _listState.update { currentState ->
                    currentState.copy(events = result.data ?: emptyList())
                }
            }
        }
    }

    private fun processLoadEvents() {
        val lastEventId = _listState.value.events.lastOrNull()?.id
        lastEventId?.let {
            viewModelScope.launch {
                getEventsUseCase(lastEventId).collect { result ->
                    _listState.update { currentState ->
                        val newEvents = result.data ?: emptyList()
                        currentState.copy(events = currentState.events + newEvents)
                    }
                }
            }
        }
    }
}
