package com.example.evops.screens.evendetails.data.api

import com.example.evops.core.data.model.EventWrapperDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventDetailsApi {
    @GET("/v1/events/{id}")
    suspend fun getEventDetails(@Query("id") eventId: String): EventWrapperDto
}