package com.example.evops.core.data.network.api

import com.example.evops.core.data.model.auth.CredentialsWrapperDto
import com.example.evops.core.data.model.auth.RefreshTokenWrapperDto
import com.example.evops.core.data.model.auth.SignupFormWrapperDto
import com.example.evops.core.data.model.auth.TokensWrapperDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/v1/auth/login")
    suspend fun login(@Body credentials: CredentialsWrapperDto): Response<TokensWrapperDto>

    @POST("/v1/auth/refresh")
    suspend fun refresh(@Body refreshToken: RefreshTokenWrapperDto): Response<TokensWrapperDto>

    @POST("/v1/auth/signup") suspend fun signup(@Body form: SignupFormWrapperDto): Response<TokensWrapperDto>
}
