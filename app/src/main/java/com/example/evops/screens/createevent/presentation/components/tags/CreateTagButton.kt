package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun CreateTagButton(onEvent: (CreateEventEvent) -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = { onEvent(CreateEventEvent.OpenHideAddTagForm(true)) }, modifier = modifier) {
        Text(stringResource(R.string.create_tag))
    }
}
