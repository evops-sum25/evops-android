package com.example.evops.screens.eventlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.usecases.GetEventsUseCase
import com.example.evops.screens.eventlist.domain.usecases.GetSearchedEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EventListViewModel
@Inject
constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val getSearchedEventsUseCase: GetSearchedEventsUseCase,
) : ViewModel() {
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
            is EventListEvent.LoadFirstEvents ->
                processLoadFirstEvents(searchString = event.searchString)
            is EventListEvent.LoadEvents -> processLoadEvents(searchString = event.searchString)
            is EventListEvent.UpdateSearchString -> {
                _listState.update { currentState ->
                    currentState.copy(searchString = event.searchString)
                }
            }
        }
    }

    private fun processLoadFirstEvents(searchString: String? = null) {
        val flow =
            when {
                searchString.isNullOrEmpty() -> getEventsUseCase()
                else -> getSearchedEventsUseCase(searchString)
            }
        viewModelScope.launch { flow.collect { result -> clearAndAppendEvents(result.data) } }
    }

    private fun processLoadEvents(searchString: String? = null) {
        val lastEventId = _listState.value.events.lastOrNull()?.id ?: return
        val flow =
            when {
                searchString.isNullOrEmpty() -> getEventsUseCase(lastEventId)
                else -> getSearchedEventsUseCase(searchString, lastEventId)
            }
        viewModelScope.launch { flow.collect { result -> appendEvents(result.data) } }
    }

    private fun appendEvents(newEvents: List<EventItem>?) {
        _listState.update { currentState ->
            currentState.copy(events = currentState.events + (newEvents ?: emptyList()))
        }
    }

    private fun clearAndAppendEvents(newEvents: List<EventItem>?) {
        _listState.update { currentState -> currentState.copy(events = newEvents ?: emptyList()) }
    }
}
