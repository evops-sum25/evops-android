package com.example.evops.screens.createevent.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.components.CreateEventScreenContent
import kotlinx.coroutines.launch

@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateEventViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val formState by viewModel.formState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val mediaPickerLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.PickMultipleVisualMedia(
                    maxItems = formState.maxSelectableItems.takeUnless { it == 0 } ?: 2
                ),
            onResult = { uris ->
                viewModel.onEvent(CreateEventEvent.AddImages(uris))
                viewModel.onEvent(CreateEventEvent.OpenHideImagePicker(shouldOpen = false))
            },
        )

    LaunchedEffect(formState.isImagePickerOpened) {
        if (formState.isImagePickerOpened) {
            mediaPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    }

    val message = stringResource(R.string.event_successfully_posted)
    LaunchedEffect(formState.isSnackbarShown) {
        if (formState.isSnackbarShown) {
            coroutineScope.launch { snackbarHostState.showSnackbar(message) }
            viewModel.onEvent(CreateEventEvent.HideShackbar)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPaddings ->
        CreateEventScreenContent(
            formState = formState,
            onEvent = viewModel::onEvent,
            modifier = modifier.padding(innerPaddings).fillMaxSize(),
        )
    }
}
