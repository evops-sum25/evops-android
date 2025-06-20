package com.example.evops.screens.eventlist.domain.model

data class EventItemPublisher(
    val id: String = "", // TODO("change to not have a default value")
    val name: String,
    val avatarPreviewUrl: String,
)
