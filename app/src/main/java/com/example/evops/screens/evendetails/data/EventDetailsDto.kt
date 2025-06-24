package com.example.evops.screens.evendetails.data

import com.example.evops.core.data.model.EventDto
import kotlinx.serialization.Serializable

@Serializable
data class EventDetailsDto(
    val event: EventDto
)
