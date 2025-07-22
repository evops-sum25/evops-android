package com.example.evops.screens.auth.presentation.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.evops.R
import com.example.evops.screens.auth.presentation.AuthEvent

@Composable
fun LoginButton(
    onEvent: (AuthEvent) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(onClick = { onEvent(AuthEvent.Login) }, enabled = enabled, modifier = modifier) {
        Text(stringResource(R.string.log_in))
    }
}
