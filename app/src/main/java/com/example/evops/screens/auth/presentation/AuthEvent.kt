package com.example.evops.screens.auth.presentation

sealed interface AuthEvent {
    data class UpdateLogin(val login: String) : AuthEvent

    data class UpdatePassword(val password: String) : AuthEvent

    data object SignUp : AuthEvent

    data object Login : AuthEvent
}
