package com.example.evops.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable data class CredentialsDto(val login: String, val password: String)
