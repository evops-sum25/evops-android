package com.example.evops.screens.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evops.screens.auth.presentation.AuthEvent
import com.example.evops.screens.auth.presentation.components.buttons.LoginButton
import com.example.evops.screens.auth.presentation.components.buttons.SwitchToSignUpButton
import com.example.evops.screens.auth.presentation.components.text.LoginTextField
import com.example.evops.screens.auth.presentation.components.text.PasswordTextField
import com.example.evops.screens.auth.presentation.state.AuthState

@Composable
fun LoginScreenContent(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 48.dp).fillMaxSize().imePadding(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End,
        ) {
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
                SwitchToSignUpButton(onEvent = onEvent)
                LoginButton(onEvent = onEvent, navController = navController)
            }
        }
    }
}
