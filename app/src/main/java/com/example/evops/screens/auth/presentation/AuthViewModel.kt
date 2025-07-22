package com.example.evops.screens.auth.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.R
import com.example.evops.core.domain.repository.AuthRepository
import com.example.evops.screens.auth.domain.model.LoginCredentials
import com.example.evops.screens.auth.domain.model.SignupForm
import com.example.evops.screens.auth.presentation.state.AuthScreen
import com.example.evops.screens.auth.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject
constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {
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
                    if (!authRepository.signup(form)) {
                        onEvent(
                            AuthEvent.ShowSnackbar(context.getString(R.string.cannot_sign_message))
                        )
                    }
                }
            }
            is AuthEvent.Login -> {
                viewModelScope.launch {
                    val credentials =
                        LoginCredentials(
                            login = _authState.value.login,
                            password = _authState.value.password,
                        )
                    if (!authRepository.login(credentials)) {
                        onEvent(
                            AuthEvent.ShowSnackbar(context.getString(R.string.cannot_login_message))
                        )
                    }
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
            is AuthEvent.ShowSnackbar -> {
                _authState.update { currentState ->
                    currentState.copy(snackbarMessage = event.message)
                }
            }
            is AuthEvent.HideSnackbar -> {
                _authState.update { currentState -> currentState.copy(snackbarMessage = "") }
            }
        }
    }
}
