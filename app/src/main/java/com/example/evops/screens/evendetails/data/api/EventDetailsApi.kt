package com.example.evops.screens.evendetails.data.api

import com.example.evops.core.data.model.event.EventWrapperDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EventDetailsApi {
    @GET("/v1/events/{event-id}")
    suspend fun getEventDetails(@Path("event-id") eventId: String): EventWrapperDto
}
