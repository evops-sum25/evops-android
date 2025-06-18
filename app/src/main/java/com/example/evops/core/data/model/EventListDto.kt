package com.example.evops.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EventListDto(
    val events: List<EventDto>
)