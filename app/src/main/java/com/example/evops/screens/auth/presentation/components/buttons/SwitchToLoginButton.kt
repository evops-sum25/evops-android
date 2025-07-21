package com.example.evops.screens.auth.presentation.components.buttons

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.evops.R
import com.example.evops.screens.auth.presentation.AuthEvent

@Composable
fun SwitchToLoginButton(onEvent: (AuthEvent) -> Unit, modifier: Modifier = Modifier) {
    TextButton(onClick = { onEvent(AuthEvent.SwitchToLogin) }, modifier = modifier) {
        Text(stringResource(R.string.switch_to_log_in))
    }
}
