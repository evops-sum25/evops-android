package com.example.evops.screens.createevent.presentation

data class CreateEventState(
    val title: String = "",
    val description: String = "",
    val withAttendance: Boolean = false,
)
