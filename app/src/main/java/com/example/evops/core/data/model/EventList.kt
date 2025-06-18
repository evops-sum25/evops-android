package com.example.evops.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EventList(
    val events: List<Event>
)