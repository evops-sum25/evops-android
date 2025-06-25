package com.example.evops.screens.createevent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.usecases.CreateEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val createEventUseCase: CreateEventUseCase
) : ViewModel() {

    private val _formState = MutableStateFlow(CreateEventState())
    val formState = _formState

    fun onEvent(event: CreateEventEvent) {
        when (event) {
            is CreateEventEvent.UpdateTitle -> {
                _formState.update { currentState ->
                    currentState.copy(title = event.title)
                }
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
                    createEventUseCase(_formState.value.toDomain())
                }
            }
        }
    }

    private fun CreateEventState.toDomain() = CreateEventForm(
        description = this.description,
        imageUrls = emptyList(),
        tagIds = emptyList(),
        title = this.title,
        withAttendance = this.withAttendance
    )
}