package com.example.evops.screens.createevent.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.evops.screens.createevent.presentation.components.DescriptionTextField
import com.example.evops.screens.createevent.presentation.components.SubmitButton
import com.example.evops.screens.createevent.presentation.components.TitleTextField
import com.example.evops.screens.createevent.presentation.components.WithAttendanceSwitch

@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateEventViewModel = hiltViewModel(),
) {
    val formState by viewModel.formState.collectAsState()
    val isSubmitButtonActive = formState.title.isNotBlank() && formState.description.isNotBlank()
    val mediaPickerLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.PickMultipleVisualMedia(
                    maxItems = formState.maxSelectableItems
                ),
            onResult = { uris ->
                viewModel.onEvent(CreateEventEvent.UpdateImages(uris))
                viewModel.onEvent(CreateEventEvent.OpenHideImagePicker(false))
            },
        )

    LaunchedEffect(formState.isImagePickerOpened) {
        if (formState.isImagePickerOpened) {
            mediaPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 12.dp).fillMaxSize(),
    ) {
        TitleTextField(
            title = formState.title,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        DescriptionTextField(
            description = formState.description,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        WithAttendanceSwitch(
            withAttendance = formState.withAttendance,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        Button(onClick = { viewModel.onEvent(CreateEventEvent.OpenHideImagePicker(true)) }) {
            Text("Open Image Picker")
        }
        SubmitButton(isActive = isSubmitButtonActive, onEvent = viewModel::onEvent)
    }
}
