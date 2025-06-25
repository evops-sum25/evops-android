package com.example.evops.screens.createevent.data.api

import com.example.evops.screens.createevent.data.model.FormWrapperDto
import com.example.evops.screens.evendetails.data.EventDetailsDto
import retrofit2.http.Body
import retrofit2.http.GET

interface CreateEventApi {
    @GET("/v1/events/create")
    suspend fun getEventDetails(@Body formDto: FormWrapperDto): EventDetailsDto
}