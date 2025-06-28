package com.example.evops.screens.createevent.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAuthorFormWrapperDto(
    @SerializedName("form")
    val authorForm: CreateAuthorFormDto,
)
