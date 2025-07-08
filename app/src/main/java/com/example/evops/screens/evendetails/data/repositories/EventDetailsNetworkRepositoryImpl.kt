package com.example.evops.screens.evendetails.data.repositories

import com.example.evops.screens.evendetails.data.api.EventDetailsApi
import com.example.evops.screens.evendetails.data.mappers.EventDetailsMapper.toDomain
import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.domain.repositories.EventDetailsNetworkRepository
import javax.inject.Inject

class EventDetailsNetworkRepositoryImpl @Inject constructor(private val api: EventDetailsApi) :
    EventDetailsNetworkRepository {
    override suspend fun getEventDetails(eventId: String): EventDetails {
        return api.getEventDetails(eventId).toDomain()
    }
}
