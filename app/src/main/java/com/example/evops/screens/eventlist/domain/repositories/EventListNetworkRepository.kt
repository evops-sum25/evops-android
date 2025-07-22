package com.example.evops.screens.eventlist.domain.repositories

import com.example.evops.screens.eventlist.domain.model.EventItem

interface EventListNetworkRepository {
    suspend fun getEvents(
        lastEventId: String? = null,
        searchString: String? = null,
        tagIds: List<String> = emptyList(),
    ): List<EventItem>
}
