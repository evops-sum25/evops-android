package com.example.evops.core.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: String,
    val name: String,
    @SerializedName("profile_picture_url")
    val profilePictureUrl: String?
)