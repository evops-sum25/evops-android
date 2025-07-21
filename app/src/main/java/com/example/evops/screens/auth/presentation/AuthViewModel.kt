package com.example.evops.screens.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.auth.domain.model.LoginCredentials
import com.example.evops.screens.auth.domain.model.SignupForm
import com.example.evops.core.domain.repository.AuthRepository
import com.example.evops.screens.auth.presentation.state.AuthScreen
import com.example.evops.screens.auth.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
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
            is AuthEvent.SignUp -> {
                viewModelScope.launch {
                    val form =
                        SignupForm(
                            login = _authState.value.login,
                            displayName = _authState.value.displayName,
                            password = _authState.value.password,
                        )
                    authRepository.signup(form)
                }
            }
            is AuthEvent.Login -> {
                viewModelScope.launch {
                    val credentials =
                        LoginCredentials(
                            login = _authState.value.login,
                            password = _authState.value.password,
                        )
                    authRepository.login(credentials)
                }
            }
            is AuthEvent.SwitchToLogin -> {
                _authState.value = AuthState(currentScreen = AuthScreen.LOGIN)
            }
            is AuthEvent.SwitchToSignUp -> {
                _authState.value = AuthState(currentScreen = AuthScreen.SIGN_UP)
            }
            is AuthEvent.UpdateDisplayName -> {
                _authState.update { currentState ->
                    currentState.copy(displayName = event.displayName)
                }
            }
        }
    }
}
