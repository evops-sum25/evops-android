package com.example.evops.screens.auth.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.UpdateLogin -> {
                _authState.update { currentState -> currentState.copy(login = event.login) }
            }
            is AuthEvent.UpdatePassword -> {
                _authState.update { currentState -> currentState.copy(password = event.password) }
            }
            is AuthEvent.SignUp -> {}
            is AuthEvent.Login -> {}
        }
    }
}
