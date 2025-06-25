package com.example.evops.screens.createevent.domain.model

data class CreateEventForm(
    val authorId: String,
    val description: String,
    val imageUrls: List<String>,
    val tagIds: List<String>,
    val title: String,
    val withAttendance: Boolean
)
