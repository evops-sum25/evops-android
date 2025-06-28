package com.example.evops.screens.eventlist.data.api

import com.example.evops.screens.eventlist.data.dto.EventListDto
import retrofit2.http.GET

interface EventListApi {
    @GET("/v1/events")
    suspend fun getEvents(): EventListDto
}
