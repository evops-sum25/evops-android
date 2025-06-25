package com.example.evops.screens.createevent.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateEventViewModel = hiltViewModel()
) {
    val formState by viewModel.formState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            value = TextFieldValue(formState.title),
            onValueChange = { viewModel.onEvent(CreateEventEvent.UpdateTitle(it.text)) }
        )
        TextField(
            value = TextFieldValue(formState.description),
            onValueChange = { viewModel.onEvent(CreateEventEvent.UpdateDescription(it.text)) }
        )
        Switch(
            checked = formState.withAttendance,
            onCheckedChange = { viewModel.onEvent(CreateEventEvent.UpdateWithAttendance(it)) }
        )
        Button(onClick = { viewModel.onEvent(CreateEventEvent.SubmitEvent) }) { Text("Submit") }
    }
}