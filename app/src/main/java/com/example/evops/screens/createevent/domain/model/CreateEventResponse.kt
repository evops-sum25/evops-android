package com.example.evops.screens.createevent.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable data class CreateEventResponse(@SerializedName("event_id") val eventId: String)
