package com.example.evops.screens.auth.presentation

sealed interface AuthEvent {
    data class UpdateLogin(val login: String) : AuthEvent

    data class UpdatePassword(val password: String) : AuthEvent

    data class UpdateDisplayName(val displayName: String) : AuthEvent

    data object SignUp : AuthEvent

    data object Login : AuthEvent

    data object SwitchToSignUp : AuthEvent

    data object SwitchToLogin : AuthEvent

    data class ShowSnackbar(val message: String) : AuthEvent

    data object HideSnackbar : AuthEvent
}
