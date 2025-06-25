package com.example.evops.screens.createevent.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserFormWrapperDto(
    val form: CreateUserFormDto
)