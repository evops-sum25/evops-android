package com.example.evops.core.data.api

import com.example.evops.core.data.dto.ExampleDto
import retrofit2.http.Body
import retrofit2.http.POST


interface ExampleApi {
    @POST("/v1/event/create")
    suspend fun createEvent(@Body exampleDto: ExampleDto): ExampleDto
}