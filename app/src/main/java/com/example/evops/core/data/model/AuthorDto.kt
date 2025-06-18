package com.example.evops.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: String,
    val name: String,
    @SerialName("profile_picture_url")
    val profilePictureUrl: String?
)