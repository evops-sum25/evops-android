package com.example.evops.core.data.model.event

import com.example.evops.core.data.model.author.AuthorDto
import com.example.evops.core.data.model.tag.TagDto
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    val author: AuthorDto,
    @SerializedName("created_at") val createdAt: String,
    val description: String,
    val id: String,
    @SerializedName("image_ids") val imageIds: List<String>,
    @SerializedName("modified_at") val modifiedAt: String,
    val tags: List<TagDto>,
    val title: String,
    @SerializedName("with_attendance") val withAttendance: Boolean,
)
