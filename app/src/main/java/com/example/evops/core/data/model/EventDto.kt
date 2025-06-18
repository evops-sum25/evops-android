package com.example.evops.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    val author: AuthorDto,
    @SerialName("created_at")
    val createdAt: String,
    val description: String,
    val id: String,
    @SerialName("image_urls")
    val imageUrls: List<String>,
    @SerialName("modified_at")
    val modifiedAt: String,
    val tags: List<TagDto>,
    val title: String,
    @SerialName("with_attendance")
    val withAttendance: Boolean
)