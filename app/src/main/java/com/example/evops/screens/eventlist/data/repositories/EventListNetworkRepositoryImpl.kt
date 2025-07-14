package com.example.evops.screens.eventlist.data.repositories

import com.example.evops.core.common.Config
import com.example.evops.screens.eventlist.data.api.EventListApi
import com.example.evops.screens.eventlist.data.mappers.EventItemMapper.toDomain
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.repositories.EventListNetworkRepository
import javax.inject.Inject

class EventListNetworkRepositoryImpl @Inject constructor(private val api: EventListApi) :
    EventListNetworkRepository {
    override suspend fun getEvents(lastEventId: String?): List<EventItem> {
        lastEventId?.let {
            return api.getEvents(limit = Config.EVENT_PAGE_LIMIT, lastEventId = lastEventId)
                .toDomain()
        }
        return api.getFirstEvents(limit = Config.EVENT_PAGE_LIMIT).toDomain()
    }
}
