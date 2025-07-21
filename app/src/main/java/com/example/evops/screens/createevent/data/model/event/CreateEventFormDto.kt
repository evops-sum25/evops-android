package com.example.evops.screens.createevent.data.model.event

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventFormDto(
    val description: String,
    @SerializedName("tag_ids") val tagIds: List<String>,
    val title: String,
)
