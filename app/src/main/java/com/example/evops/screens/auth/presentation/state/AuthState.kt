package com.example.evops.screens.auth.presentation.state

data class AuthState(
    val login: String = "",
    val password: String = "",
    val displayName: String = "",
    val currentScreen: AuthScreen = AuthScreen.LOGIN,
    val snackbarMessage: String? = null,
)
