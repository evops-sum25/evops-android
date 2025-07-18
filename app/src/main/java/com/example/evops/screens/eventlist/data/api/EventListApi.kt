package com.example.evops.screens.eventlist.data.api

import com.example.evops.screens.eventlist.data.dto.EventListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventListApi {
    @GET("/v1/events") suspend fun getFirstEvents(@Query("limit") limit: Int): EventListDto

    @GET("/v1/events")
    suspend fun getEvents(
        @Query("limit") limit: Int,
        @Query("last-id") lastEventId: String,
    ): EventListDto
}
