package com.example.evops.screens.evendetails.presentation

import com.example.evops.screens.evendetails.domain.model.EventDetails

data class EventDetailsState(
    val eventDetails: EventDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
