package com.example.evops.core.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    val author: UserDto,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val id: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("modified_at")
    val modifiedAt: String,
    val tags: List<TagDto>,
    val title: String,
    @SerializedName("with_attendance")
    val withAttendance: Boolean
)