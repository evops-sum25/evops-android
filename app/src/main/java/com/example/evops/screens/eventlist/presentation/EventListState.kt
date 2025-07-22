package com.example.evops.screens.eventlist.presentation

import com.example.evops.screens.eventlist.domain.model.EventItem

data class EventListState(
    val events: List<EventItem>? = null,
    val searchString: String = "",
    val searchTagIds: List<String> = emptyList(),
)
