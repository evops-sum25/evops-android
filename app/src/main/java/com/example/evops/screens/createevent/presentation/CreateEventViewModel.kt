package com.example.evops.screens.createevent.presentation

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.R
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.usecases.CreateEventUseCase
import com.example.evops.screens.createevent.domain.usecases.CreateTagUseCase
import com.example.evops.screens.createevent.domain.usecases.GetTagUseCase
import com.example.evops.screens.createevent.domain.usecases.GetTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel
@Inject
constructor(
    private val createEventUseCase: CreateEventUseCase,
    private val getTagsUseCase: GetTagsUseCase,
    private val createTagUseCase: CreateTagUseCase,
    private val getTagUseCase: GetTagUseCase,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _formState =
        MutableStateFlow(savedStateHandle.get<CreateEventState>("formState") ?: CreateEventState())
    val formState = _formState

    private val _tagFormState = MutableStateFlow(CreateTagState())
    val tagFormState = _tagFormState

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
                val shackbarMessage = context.getString(R.string.event_successfully_posted)
                _formState.value = CreateEventState(snackbarMessage = shackbarMessage)
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
            is CreateEventEvent.DeleteImage -> {
                _formState.update { currentState ->
                    currentState.copy(
                        selectedUris = currentState.selectedUris.removedAt(event.imageId)
                    )
                }
            }
            is CreateEventEvent.HideShackbar -> {
                _formState.update { currentState -> currentState.copy(snackbarMessage = null) }
            }
            is CreateEventEvent.UpdateSearchingTagName -> {
                _formState.update { currentState ->
                    currentState.copy(searchingTagName = event.name)
                }
            }
            is CreateEventEvent.OpenHideAddTagForm -> {
                _formState.update { currentState ->
                    currentState.copy(isAddTagFormOpen = event.shouldOpen)
                }
            }
            is CreateEventEvent.AddTagAlias -> {
                _tagFormState.update { currentState ->
                    currentState.copy(aliases = currentState.aliases + "")
                }
            }
            is CreateEventEvent.RemoveTagAlias -> {
                _tagFormState.update { currentState ->
                    val aliases = currentState.aliases.removedAt(event.tagId)
                    currentState.copy(aliases = aliases)
                }
            }
            is CreateEventEvent.GetTags -> {
                viewModelScope.launch {
                    getTagsUseCase(_formState.value.searchingTagName).collect { result ->
                        if (result.data?.isEmpty() ?: false) {
                            val shackbarMessage = context.getString(R.string.no_tags_found)
                            _formState.value = CreateEventState(snackbarMessage = shackbarMessage)
                        }
                        _formState.update { currentState ->
                            val tags = (result.data ?: emptyList()).map { it.toUi() }
                            currentState.copy(suggestedTags = tags)
                        }
                    }
                }
            }
            is CreateEventEvent.SubmitTag -> {
                viewModelScope.launch {
                    createTagUseCase(tagForm = _tagFormState.value.toDomain()).collect()
                    onEvent(CreateEventEvent.GetTags)
                }
                _formState.update { currentState -> currentState.copy(isAddTagFormOpen = false) }
                _tagFormState.value = CreateTagState()
            }

            is CreateEventEvent.UpdateTagAlias -> {
                _tagFormState.update { currentState ->
                    val tagAliases = currentState.aliases.updated(event.id, event.alias)
                    currentState.copy(aliases = tagAliases)
                }
            }
            is CreateEventEvent.UpdateTagName -> {
                _tagFormState.update { currentState -> currentState.copy(name = event.name) }
            }
            is CreateEventEvent.SelectTag -> {
                _formState.update { currentState ->
                    currentState.copy(selectedTags = currentState.selectedTags + event.tag)
                }
            }
            is CreateEventEvent.UnselectTag -> {
                _formState.update { currentState ->
                    currentState.copy(selectedTags = currentState.selectedTags - event.tag)
                }
            }
            is CreateEventEvent.DropAddTagForm -> {
                _formState.update { currentState -> currentState.copy(isAddTagFormOpen = false) }
                _tagFormState.value = CreateTagState()
            }
        }
    }

    private fun CreateEventState.toDomain() =
        CreateEventForm(
            description = this.description,
            tagIds = this.selectedTags.map { it.id },
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

    private fun <T> List<T>.updated(index: Int, value: T): List<T> {
        return toMutableList().apply { this[index] = value }
    }

    private fun <T> List<T>.removedAt(index: Int): List<T> {
        return take(index) + drop(index + 1)
    }
}
