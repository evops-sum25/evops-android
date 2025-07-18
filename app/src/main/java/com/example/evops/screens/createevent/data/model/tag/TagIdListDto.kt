package com.example.evops.screens.createevent.data.model.tag

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TagIdListDto(
    @SerializedName("tag_ids")
    val tagIds: List<String>
)
