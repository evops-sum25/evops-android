package com.example.evops.screens.settings.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val login: String,
    @SerializedName("display_name") val displayName: String,
)
