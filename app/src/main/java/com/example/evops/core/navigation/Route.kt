package com.example.evops.core.navigation

import kotlinx.serialization.Serializable

object Route {
    @Serializable
    data object EventList

    @Serializable
    data class EventDetails(val eventId: String)
}