package com.example.evops.screens.evendetails.domain.model

data class EventDetails(
    val author: EventDetailsAuthor,
    val imageUrls: List<String>,
    val title: String,
    val description: String,
    val tagsData: List<EventDetailsTag>,
)
