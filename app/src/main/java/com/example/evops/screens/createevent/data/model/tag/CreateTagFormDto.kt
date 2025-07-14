package com.example.evops.screens.createevent.data.model.tag

import kotlinx.serialization.Serializable

@Serializable data class CreateTagFormDto(val name: String, val aliases: List<String>)
