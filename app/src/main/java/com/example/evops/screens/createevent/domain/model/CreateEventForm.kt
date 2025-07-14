package com.example.evops.screens.createevent.domain.model

data class CreateEventForm(
    val description: String,
    val tagIds: List<String>,
    val title: String,
    val withAttendance: Boolean,
)
