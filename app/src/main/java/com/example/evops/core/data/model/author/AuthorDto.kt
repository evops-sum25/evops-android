package com.example.evops.core.data.model.author

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(val id: String, @SerializedName("display_name") val name: String)
