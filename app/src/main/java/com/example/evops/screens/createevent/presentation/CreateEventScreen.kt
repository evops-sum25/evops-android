package com.example.evops.screens.createevent.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.evops.R
import com.example.evops.core.presentation.components.topbar.TitledTopBar
import com.example.evops.screens.createevent.presentation.components.CreateEventScreenContent
import com.example.evops.screens.createevent.presentation.components.buttons.SubmitButton
import com.example.evops.screens.createevent.presentation.components.tags.CreateTagDialog
import com.example.evops.screens.createevent.presentation.components.tags.SuggestedTagsFormDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateEventViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val formState by viewModel.formState.collectAsState()
    val tagFormState by viewModel.tagFormState.collectAsState()
    val snackbarState by viewModel.snackbarState.collectAsState()
    val suggestedTagsFormState by viewModel.suggestedTagsFormState.collectAsState()
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

    LaunchedEffect(snackbarState) {
        snackbarState.message?.let { message ->
            showSnackbar(
                coroutineScope = coroutineScope,
                snackbarHostState = snackbarHostState,
                snackbarMessage = message,
                snackbarActionLabel = snackbarState.actionLabel,
                onEvent = viewModel::onEvent,
            )
            viewModel.onEvent(CreateEventEvent.HideShackbar)
        }
    }

    val isSubmitButtonActive = formState.title.isNotBlank() && formState.description.isNotBlank()
    Scaffold(
        topBar = { TitledTopBar(title = stringResource(R.string.event_details)) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            SubmitButton(isActive = isSubmitButtonActive, onEvent = viewModel::onEvent)
        },
        floatingActionButtonPosition = FabPosition.End,
        contentWindowInsets = WindowInsets(0.dp),
        modifier = modifier,
    ) { innerPaddings ->
        CreateEventScreenContent(
            formState = formState,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(innerPaddings).fillMaxSize(),
        )
    }
    if (formState.isAddTagFormOpen) {
        CreateTagDialog(tagFormState = tagFormState, onEvent = viewModel::onEvent)
    }
    if (formState.isSuggestedTagsFormOpen) {
        SuggestedTagsFormDialog(
            suggestedTagsFormState = suggestedTagsFormState,
            onEvent = viewModel::onEvent,
        )
    }
}

private fun showSnackbar(
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    snackbarMessage: String,
    snackbarActionLabel: String?,
    onEvent: (CreateEventEvent) -> Unit,
) {
    coroutineScope.launch {
        snackbarHostState
            .showSnackbar(
                message = snackbarMessage,
                actionLabel = snackbarActionLabel,
                duration = SnackbarDuration.Short,
            )
            .run {
                when (this) {
                    SnackbarResult.Dismissed -> onEvent(CreateEventEvent.HideShackbar)
                    SnackbarResult.ActionPerformed ->
                        onEvent(CreateEventEvent.OpenHideAddTagForm(true))
                }
            }
    }
}
