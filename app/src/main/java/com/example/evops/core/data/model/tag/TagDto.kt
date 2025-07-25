package com.example.evops.core.data.model.tag

import kotlinx.serialization.Serializable

@Serializable data class TagDto(val aliases: List<String>, val id: String, val name: String)
