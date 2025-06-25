package com.example.evops.screens.createevent.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateEventFormWrapperDto(
    val form: CreateEventFormDto
)