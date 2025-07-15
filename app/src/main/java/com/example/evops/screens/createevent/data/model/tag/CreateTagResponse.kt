package com.example.evops.screens.createevent.data.model.tag

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable data class CreateTagResponse(@SerializedName("tag_id") val tagId: String)
