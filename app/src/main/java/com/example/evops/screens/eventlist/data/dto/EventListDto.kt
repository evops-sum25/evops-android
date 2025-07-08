package com.example.evops.screens.eventlist.data.dto

import com.example.evops.core.data.model.event.EventDto
import kotlinx.serialization.Serializable

@Serializable data class EventListDto(val events: List<EventDto>)
