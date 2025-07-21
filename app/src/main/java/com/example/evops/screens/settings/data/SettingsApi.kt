package com.example.evops.screens.settings.data

import com.example.evops.screens.settings.data.model.UserWrapperDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SettingsApi {
    @GET("/v1/auth/me")
    suspend fun getUserName(@Header("Authorization") accessToken: String): Response<UserWrapperDto>
}
