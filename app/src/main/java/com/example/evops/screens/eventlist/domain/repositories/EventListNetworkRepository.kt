package com.example.evops.screens.eventlist.domain.repositories

import com.example.evops.screens.eventlist.domain.model.EventItem

interface EventListNetworkRepository {
    suspend fun getEvents(lastEventId: String? = null): List<EventItem>

    suspend fun getSearchedEvents(
        searchString: String,
        lastEventId: String? = null,
    ): List<EventItem>
}
