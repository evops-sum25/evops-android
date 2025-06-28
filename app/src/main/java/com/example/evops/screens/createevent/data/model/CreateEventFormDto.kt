package com.example.evops.screens.createevent.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventFormDto(
    @SerializedName("author_id")
    val authorId: String,
    val description: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("tag_ids")
    val tagIds: List<String>,
    val title: String,
    @SerializedName("with_attendance")
    val withAttendance: Boolean,
)
