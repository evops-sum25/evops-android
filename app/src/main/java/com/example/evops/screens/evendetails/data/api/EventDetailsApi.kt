package com.example.evops.screens.evendetails.data.api

import com.example.evops.core.data.model.event.EventWrapperDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EventDetailsApi {
    @GET("/v1/events/{id}")
    suspend fun getEventDetails(@Path("id") eventId: String): EventWrapperDto
}
