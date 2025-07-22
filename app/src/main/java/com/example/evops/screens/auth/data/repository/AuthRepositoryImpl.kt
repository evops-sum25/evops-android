package com.example.evops.screens.auth.data.repository

import com.example.evops.core.common.exceptions.RefreshTokenExpiredException
import com.example.evops.core.data.datastore.AuthDataStore
import com.example.evops.core.data.datastore.AuthState
import com.example.evops.core.data.model.auth.RefreshTokenWrapperDto
import com.example.evops.core.data.model.auth.TokensDto
import com.example.evops.core.data.network.api.AuthApi
import com.example.evops.core.domain.repository.AuthRepository
import com.example.evops.screens.auth.data.mappers.AuthMapper.toData
import com.example.evops.screens.auth.domain.model.LoginCredentials
import com.example.evops.screens.auth.domain.model.SignupForm
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(private val authApi: AuthApi, private val authDataStore: AuthDataStore) :
    AuthRepository {
    override suspend fun login(credentials: LoginCredentials): Boolean {
        authApi.login(credentials = credentials.toData()).body()?.tokens?.let { tokens ->
            updateTokens(tokens)
            authDataStore.updateAuthState(AuthState.AUTHORIZED)
            return true
        }
        return false
    }

    override suspend fun signup(form: SignupForm): Boolean {
        authApi.signup(form = form.toData()).body()?.tokens?.let { tokens ->
            updateTokens(tokens)
            authDataStore.updateAuthState(AuthState.AUTHORIZED)
            return true
        }
        return false
    }

    override suspend fun refresh() {
        val token = authDataStore.refreshToken.first()
        token?.let { refreshToken ->
            authApi.refresh(RefreshTokenWrapperDto(refreshToken)).body()?.tokens?.let { tokens ->
                updateTokens(tokens)
                authDataStore.updateAuthState(AuthState.AUTHORIZED)
            } ?: authDataStore.updateAuthState(AuthState.NEED_LOGIN)
        } ?: throw RefreshTokenExpiredException()
    }

    private suspend fun updateTokens(tokens: TokensDto) {
        authDataStore.updateAccessToken(tokens.access)
        authDataStore.updateRefreshToken(tokens.refresh)
    }
}
