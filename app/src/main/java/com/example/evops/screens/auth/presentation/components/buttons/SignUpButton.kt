package com.example.evops.screens.auth.presentation.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.evops.R
import com.example.evops.screens.auth.presentation.AuthEvent

@Composable
fun SignUpButton(onEvent: (AuthEvent) -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = { onEvent(AuthEvent.SignUp) }, modifier = modifier) {
        Text(stringResource(R.string.sign_up))
    }
}
