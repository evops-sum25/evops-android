package com.example.evops.core.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class EventWrapperDto(
    val event: EventDto,
)
