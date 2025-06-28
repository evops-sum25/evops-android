package com.example.evops.core.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserWrapperDto(
    val user: UserDto,
)
