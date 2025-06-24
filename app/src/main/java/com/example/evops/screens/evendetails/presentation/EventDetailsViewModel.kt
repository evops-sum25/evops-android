package com.example.evops.screens.evendetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.evendetails.domain.usecases.GetEventDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventDetailsState = MutableStateFlow(EventDetailsState())
    val eventDetailsState = _eventDetailsState
        .onStart { loadEventDetails() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = EventDetailsState()
        )

    fun loadEventDetails() {
        savedStateHandle.get<String>("event_details_id")?.let { eventId ->
            viewModelScope.launch {
                getEventDetailsUseCase(eventId).collect { result ->
                    _eventDetailsState.update { currentState ->
                        currentState.copy(eventDetails = result.data)
                    }
                }
            }
        }
    }
}