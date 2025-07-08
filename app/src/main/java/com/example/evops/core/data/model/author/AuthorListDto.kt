package com.example.evops.core.data.model.author

import kotlinx.serialization.Serializable

@Serializable data class AuthorListDto(val authors: List<AuthorDto>)
