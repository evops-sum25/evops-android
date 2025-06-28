package com.example.evops.screens.createevent.presentation

sealed interface CreateEventEvent {
    data class UpdateTitle(val title: String) : CreateEventEvent

    data class UpdateDescription(val description: String) : CreateEventEvent

    data class UpdateWithAttendance(val withAttendance: Boolean) : CreateEventEvent

    data object SubmitEvent : CreateEventEvent
}
