package com.example.evops.core.data.model.auth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenWrapperDto(@SerializedName("refresh_token") val refreshToken: String)
