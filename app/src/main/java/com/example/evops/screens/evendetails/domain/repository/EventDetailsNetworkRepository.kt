package com.example.evops.screens.evendetails.domain.repository

import com.example.evops.screens.evendetails.domain.model.EventDetails

interface EventDetailsNetworkRepository {
    suspend fun getEventDetails(eventId: String): EventDetails
}