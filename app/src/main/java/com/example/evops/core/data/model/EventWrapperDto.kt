package com.example.evops.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EventWrapperDto(
    val event: EventDto
)