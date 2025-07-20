package com.example.evops.screens.eventlist.presentation

sealed interface EventListEvent {
    data object LoadFirstEvents : EventListEvent

    data object LoadEvents : EventListEvent

    data class UpdateSearchString(val searchString: String) : EventListEvent

    data object SearchEvents : EventListEvent
}
