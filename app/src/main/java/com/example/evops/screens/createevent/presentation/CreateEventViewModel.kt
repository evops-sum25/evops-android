package com.example.evops.screens.createevent.presentation

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.R
import com.example.evops.core.common.Resource
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.usecases.CreateEventUseCase
import com.example.evops.screens.createevent.domain.usecases.CreateTagUseCase
import com.example.evops.screens.createevent.domain.usecases.GetTagUseCase
import com.example.evops.screens.createevent.domain.usecases.GetTagsUseCase
import com.example.evops.screens.createevent.domain.usecases.SuggestTagsByDescriptionUseCase
import com.example.evops.screens.createevent.presentation.states.CreateEventSnackbarState
import com.example.evops.screens.createevent.presentation.states.CreateEventState
import com.example.evops.screens.createevent.presentation.states.CreateTagState
import com.example.evops.screens.createevent.presentation.states.SuggestedTagsFormState
import com.example.evops.screens.createevent.presentation.states.toUi
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
    private val suggestTagsByDescriptionUseCase: SuggestTagsByDescriptionUseCase,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _formState =
        MutableStateFlow(savedStateHandle.get<CreateEventState>("formState") ?: CreateEventState())
    val formState = _formState

    private val _tagFormState = MutableStateFlow(CreateTagState())
    val tagFormState = _tagFormState

    private val _snackbarState = MutableStateFlow(CreateEventSnackbarState())
    val snackbarState = _snackbarState

    private val _suggestedTagsFormState = MutableStateFlow(SuggestedTagsFormState())
    val suggestedTagsFormState = _suggestedTagsFormState

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
            is CreateEventEvent.SubmitEvent -> {
                viewModelScope.launch {
                    createEventUseCase(
                        eventForm = _formState.value.toDomain(),
                        images = _formState.value.selectedUris.map { uri -> uri.toFile(context) },
                    )
                }
                val snackbarMessage = context.getString(R.string.event_successfully_posted)
                _formState.value = CreateEventState()
                _snackbarState.update { currentState ->
                    currentState.copy(message = snackbarMessage)
                }
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
                _snackbarState.value = CreateEventSnackbarState()
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
            is CreateEventEvent.SearchTags -> {
                viewModelScope.launch {
                    Log.d("DEBB", _formState.value.searchingTagName)
                    getTagsUseCase(_formState.value.searchingTagName).collect { result ->
                        if (result.data?.isEmpty() ?: false) {
                            val snackbarMessage = context.getString(R.string.no_tags_found)
                            val snackbarActionLabel = context.getString(R.string.create_tag)
                            _snackbarState.update { currentState ->
                                currentState.copy(
                                    message = snackbarMessage,
                                    actionLabel = snackbarActionLabel,
                                )
                            }
                        }
                        _formState.update { currentState ->
                            val tags = (result.data ?: emptyList()).map { it.toUi() }
                            currentState.copy(foundTags = tags)
                        }
                    }
                }
            }
            is CreateEventEvent.SubmitTag -> {
                viewModelScope.launch {
                    createTagUseCase(tagForm = _tagFormState.value.toDomain()).collect()
                    onEvent(CreateEventEvent.SearchTags)
                }
                _formState.update { currentState -> currentState.copy(isAddTagFormOpen = false) }
                _tagFormState.value = CreateTagState()
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
            is CreateEventEvent.SuggestTagsByDescription -> {
                _suggestedTagsFormState.update { currentState ->
                    currentState.copy(isLoading = true)
                }
                _formState.update { currentState ->
                    currentState.copy(isSuggestedTagsFormOpen = true)
                }
                viewModelScope.launch {
                    suggestTagsByDescriptionUseCase(_formState.value.description).collect { result
                        ->
                        when (result) {
                            is Resource.Loading -> {
                                _suggestedTagsFormState.update { currentState ->
                                    currentState.copy(isLoading = true)
                                }
                            }
                            is Resource.Error -> {
                                val noTagsMessage = context.getString(R.string.no_tags_suggested)
                                _suggestedTagsFormState.update { currentState ->
                                    currentState.copy(isLoading = false, message = noTagsMessage)
                                }
                            }
                            is Resource.Success -> {
                                if (result.data.isEmpty()) {
                                    val noTagsMessage =
                                        context.getString(R.string.no_tags_suggested)
                                    _suggestedTagsFormState.update { currentState ->
                                        currentState.copy(
                                            isLoading = false,
                                            message = noTagsMessage,
                                        )
                                    }
                                } else {
                                    _suggestedTagsFormState.value =
                                        SuggestedTagsFormState(
                                            suggestedTags = result.data.map { it.toUi() }
                                        )
                                }
                            }
                        }
                    }
                }
            }
            CreateEventEvent.AddSuggestedTags -> {
                _formState.update { currentState ->
                    currentState.copy(
                        selectedTags = _suggestedTagsFormState.value.suggestedTags,
                        isAddTagFormOpen = false,
                    )
                }
                _suggestedTagsFormState.value = SuggestedTagsFormState()
            }
            CreateEventEvent.DropSuggestedTagsForm -> {
                _formState.update { currentState ->
                    currentState.copy(isSuggestedTagsFormOpen = false)
                }
                _suggestedTagsFormState.value = SuggestedTagsFormState()
            }
        }
    }

    private fun CreateEventState.toDomain() =
        CreateEventForm(
            description = this.description,
            tagIds = this.selectedTags.map { it.id },
            title = this.title,
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

    private fun <T> List<T>.removedAt(index: Int): List<T> {
        return take(index) + drop(index + 1)
    }
}
