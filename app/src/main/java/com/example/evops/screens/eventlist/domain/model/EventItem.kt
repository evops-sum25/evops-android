package com.example.evops.screens.eventlist.domain.model

data class EventItem(
    val id: String,
    val title: String,
    val author: EventItemAuthor,
    val imageUrl: String?,
    val description: String,
    val tags: List<EventItemTag>,
)
