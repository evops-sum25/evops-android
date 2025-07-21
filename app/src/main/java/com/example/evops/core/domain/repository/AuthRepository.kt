package com.example.evops.core.domain.repository

import com.example.evops.screens.auth.domain.model.LoginCredentials
import com.example.evops.screens.auth.domain.model.SignupForm

interface AuthRepository {
    suspend fun login(credentials: LoginCredentials)

    suspend fun signup(form: SignupForm)

    suspend fun refresh()
}
