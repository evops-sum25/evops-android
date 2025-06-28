package com.example.evops.screens.eventlist.data.repositories

import com.example.evops.screens.eventlist.data.api.EventListApi
import com.example.evops.screens.eventlist.data.mappers.EventItemMapper.toDomain
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.repositories.EventListNetworkRepository
import javax.inject.Inject

class EventListNetworkRepositoryImpl
    @Inject
    constructor(
        private val api: EventListApi,
    ) : EventListNetworkRepository {
        override suspend fun getEvents(): List<EventItem> {
            return api.getEvents().toDomain()
        }
    }
