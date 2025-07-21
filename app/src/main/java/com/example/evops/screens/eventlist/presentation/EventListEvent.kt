package com.example.evops.screens.eventlist.presentation

sealed interface EventListEvent {
    data class LoadFirstEvents(val searchString: String? = null) : EventListEvent

    data class LoadEvents(val searchString: String? = null) : EventListEvent

    data class UpdateSearchString(val searchString: String) : EventListEvent
}
