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
class EventListViewModel @Inject constructor(private val getEventsUseCase: GetEventsUseCase) : ViewModel() {

    private val _listState = MutableStateFlow(EventListState())
    val listState = _listState
        .onStart { loadEvents() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = EventListState()
        )

    fun loadEvents() {
        viewModelScope.launch {
            getEventsUseCase().collect { result ->
                _listState.update { currentState ->
                    currentState.copy(events = result.data ?: emptyList())
                }
            }
        }
    }
}