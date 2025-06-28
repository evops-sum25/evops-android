package com.example.evops.screens.evendetails.domain.repositories

import com.example.evops.screens.evendetails.domain.model.EventDetails

interface EventDetailsNetworkRepository {
    suspend fun getEventDetails(eventId: String): EventDetails
}
