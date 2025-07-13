package com.example.evops.screens.createevent.presentation

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.usecases.CreateEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CreateEventViewModel
@Inject
constructor(
    private val createEventUseCase: CreateEventUseCase,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _formState =
        MutableStateFlow(savedStateHandle.get<CreateEventState>("formState") ?: CreateEventState())
    val formState = _formState

    init {
        viewModelScope.launch {
            _formState.collect { state -> savedStateHandle["formState"] = state }
        }
    }

    fun onEvent(event: CreateEventEvent) {
        when (event) {
            is CreateEventEvent.UpdateTitle -> {
                _formState.update { currentState -> currentState.copy(title = event.title) }
            }

            is CreateEventEvent.UpdateDescription -> {
                _formState.update { currentState ->
                    currentState.copy(description = event.description)
                }
            }

            is CreateEventEvent.UpdateWithAttendance -> {
                _formState.update { currentState ->
                    currentState.copy(withAttendance = event.withAttendance)
                }
            }

            is CreateEventEvent.SubmitEvent -> {
                viewModelScope.launch {
                    createEventUseCase(
                        eventForm = _formState.value.toDomain(),
                        images = _formState.value.selectedUris.map { uri -> uri.toFile(context) },
                    )
                }
                _formState.value = CreateEventState(isSnackbarShown = true)
            }

            is CreateEventEvent.OpenHideImagePicker -> {
                _formState.update { currentState ->
                    currentState.copy(isImagePickerOpened = event.shouldOpen)
                }
            }

            is CreateEventEvent.AddImages -> {
                _formState.update { currentState ->
                    currentState.copy(selectedUris = currentState.selectedUris + event.uris)
                }
            }

            is CreateEventEvent.DeleteImages -> {
                _formState.update { currentState ->
                    currentState.copy(
                        selectedUris = currentState.selectedUris - event.uris,
                        deletingUris = emptyList(),
                    )
                }
            }

            is CreateEventEvent.AddDeletingImage -> {
                _formState.update { currentState ->
                    currentState.copy(deletingUris = currentState.deletingUris + event.uri)
                }
            }

            is CreateEventEvent.RemoveDeletingImage -> {
                _formState.update { currentState ->
                    currentState.copy(deletingUris = currentState.deletingUris - event.uri)
                }
            }

            CreateEventEvent.HideShackbar -> {
                _formState.update { currentState -> currentState.copy(isSnackbarShown = false) }
            }
        }
    }

    private fun CreateEventState.toDomain() =
        CreateEventForm(
            description = this.description,
            imageUrls = emptyList(),
            tagIds = emptyList(),
            title = this.title,
            withAttendance = this.withAttendance,
        )

    private fun getFileName(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index != -1) return it.getString(index)
            }
        }
        return null
    }

    private fun Uri.toFile(context: Context): File {
        val fileName = getFileName(context, this) ?: "temp_file"
        val tempFile = File(context.cacheDir, fileName)
        context.contentResolver.openInputStream(this)?.use { inputStream ->
            FileOutputStream(tempFile).use { outputStream -> inputStream.copyTo(outputStream) }
        }
        return tempFile
    }
}
