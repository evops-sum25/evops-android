package com.example.evops.screens.evendetails.domain.model

data class EventDetailsPublisher(
    val id: String = "", // TODO("change to not have a default value")
    val name: String,
    val avatarPreviewUrl: String,
)
