package com.example.evops.screens.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.screens.auth.presentation.AuthEvent
import com.example.evops.screens.auth.presentation.components.buttons.SignUpButton
import com.example.evops.screens.auth.presentation.components.buttons.SwitchToLoginButton
import com.example.evops.screens.auth.presentation.components.text.DisplayNameTextField
import com.example.evops.screens.auth.presentation.components.text.LoginTextField
import com.example.evops.screens.auth.presentation.components.text.PasswordTextField
import com.example.evops.screens.auth.presentation.state.AuthState
import uniffi.evops.ValidateUserDisplayNameResult
import uniffi.evops.ValidateUserLoginResult
import uniffi.evops.ValidateUserPasswordResult
import uniffi.evops.validateUserDisplayName
import uniffi.evops.validateUserLogin
import uniffi.evops.validateUserPassword

@Composable
fun SignUpScreenContent(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val canSignUp by
        remember(state) {
            derivedStateOf {
                validateUserDisplayName(state.displayName) == ValidateUserDisplayNameResult.OK &&
                    validateUserLogin(state.login) == ValidateUserLoginResult.OK &&
                    validateUserPassword(state.password) == ValidateUserPasswordResult.OK
            }
        }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 48.dp).fillMaxSize().imePadding(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End,
        ) {
            DisplayNameTextField(
                displayName = state.displayName,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
            LoginTextField(
                login = state.login,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
            PasswordTextField(
                password = state.password,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SwitchToLoginButton(onEvent = onEvent)
                SignUpButton(onEvent = onEvent, enabled = canSignUp)
            }
        }
    }
}
