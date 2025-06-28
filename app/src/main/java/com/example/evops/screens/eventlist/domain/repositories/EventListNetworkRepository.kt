package com.example.evops.screens.eventlist.domain.repositories

import com.example.evops.screens.eventlist.domain.model.EventItem

interface EventListNetworkRepository {
    suspend fun getEvents(): List<EventItem>
}
