package com.example.evops.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExampleDto(
    val description: String,
    val name: String,
)
