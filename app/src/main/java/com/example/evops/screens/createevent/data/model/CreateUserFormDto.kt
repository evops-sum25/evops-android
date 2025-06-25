package com.example.evops.screens.createevent.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserFormDto(
    val name: String,
    @SerializedName("profile_picture_url")
    val profilePictureUrl: String?
)