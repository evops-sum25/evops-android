package com.example.evops.screens.eventlist.data.api

import com.example.evops.screens.eventlist.data.dto.ExampleDto
import retrofit2.http.Body
import retrofit2.http.POST


interface ExampleApi {
    @POST("/v1/event/create")
    suspend fun createEvent(@Body exampleDto: ExampleDto): ExampleDto
}