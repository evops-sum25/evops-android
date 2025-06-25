package com.example.evops.screens.createevent.data.api

import com.example.evops.core.data.model.EventWrapperDto
import com.example.evops.core.data.model.UserListDto
import com.example.evops.core.data.model.UserWrapperDto
import com.example.evops.screens.createevent.data.model.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.data.model.CreateUserFormWrapperDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CreateEventApi {
    @POST("/v1/events/create")
    suspend fun createEvent(@Body formDto: CreateEventFormWrapperDto): EventWrapperDto

    @GET("v1/users")
    suspend fun getUsers(): UserListDto

    @POST("v1/users/create")
    suspend fun createUser(@Body formDto: CreateUserFormWrapperDto): UserWrapperDto
}