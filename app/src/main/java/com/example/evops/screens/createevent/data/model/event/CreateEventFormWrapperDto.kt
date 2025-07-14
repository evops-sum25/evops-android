package com.example.evops.screens.createevent.data.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventFormWrapperDto(@SerializedName("form") val eventForm: CreateEventFormDto)
