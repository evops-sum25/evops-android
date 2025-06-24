package com.example.evops.core.navigation

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object EventList : Destination()

    @Serializable
    data class EventDetails(val eventId: String) : Destination()

}

sealed class SubGraph {

    @Serializable
    data object Home : SubGraph()

    @Serializable
    data object AddEvent : SubGraph()
}