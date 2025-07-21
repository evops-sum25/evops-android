package com.example.evops.core.data.model.auth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SignupFormDto(
    val login: String,
    @SerializedName("display_name") val displayName: String,
    val password: String,
)
