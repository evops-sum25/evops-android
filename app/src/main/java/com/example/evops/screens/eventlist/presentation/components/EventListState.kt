package com.example.evops.screens.eventlist.presentation.components

import com.example.evops.screens.eventlist.domain.model.EventItem

data class EventListState(
    val events: List<EventItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
