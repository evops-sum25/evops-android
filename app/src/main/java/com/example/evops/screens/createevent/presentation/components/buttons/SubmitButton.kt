package com.example.evops.screens.createevent.presentation.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun SubmitButton(
    enabled: Boolean,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onEvent(CreateEventEvent.SubmitEvent) },
        enabled = enabled,
        modifier = modifier,
    ) {
        Text(text = stringResource(R.string.submit), style = MaterialTheme.typography.bodyLarge)
    }
}
