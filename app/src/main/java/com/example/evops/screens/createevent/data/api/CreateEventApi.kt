package com.example.evops.screens.createevent.data.api

import com.example.evops.core.data.model.event.EventWrapperDto
import com.example.evops.core.data.model.user.UserListDto
import com.example.evops.core.data.model.user.UserWrapperDto
import com.example.evops.screens.createevent.data.model.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.data.model.CreateUserFormWrapperDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CreateEventApi {
    @POST("/v1/events")
    suspend fun createEvent(
        @Body formDto: CreateEventFormWrapperDto,
    ): EventWrapperDto

    @GET("v1/users")
    suspend fun getUsers(): UserListDto

    @POST("v1/users")
    suspend fun createUser(
        @Body formDto: CreateUserFormWrapperDto,
    ): UserWrapperDto
}
