package com.example.evops.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable data class TokensDto(val access: String, val refresh: String)
