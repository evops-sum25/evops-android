package com.example.evops.screens.auth.data.mappers

import com.example.evops.core.data.model.auth.CredentialsDto
import com.example.evops.core.data.model.auth.CredentialsWrapperDto
import com.example.evops.core.data.model.auth.SignupFormDto
import com.example.evops.core.data.model.auth.SignupFormWrapperDto
import com.example.evops.screens.auth.domain.model.LoginCredentials
import com.example.evops.screens.auth.domain.model.SignupForm

object AuthMapper {
    fun LoginCredentials.toData() =
        CredentialsWrapperDto(
            credentials = CredentialsDto(login = this.login, password = this.password)
        )

    fun SignupForm.toData() =
        SignupFormWrapperDto(
            form =
                SignupFormDto(
                    login = this.login,
                    displayName = this.displayName,
                    password = this.password,
                )
        )
}
