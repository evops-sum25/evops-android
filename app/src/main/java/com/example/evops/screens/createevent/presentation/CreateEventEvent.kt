package com.example.evops.screens.createevent.presentation

import android.net.Uri

sealed interface CreateEventEvent {
    data class UpdateTitle(val title: String) : CreateEventEvent

    data class UpdateDescription(val description: String) : CreateEventEvent

    data class UpdateWithAttendance(val withAttendance: Boolean) : CreateEventEvent

    data object SubmitEvent : CreateEventEvent

    data class OpenHideImagePicker(val shouldOpen: Boolean) : CreateEventEvent

    data class AddImages(val uris: List<Uri>) : CreateEventEvent

    data class DeleteImages(val uris: List<Uri>) : CreateEventEvent

    data class AddDeletingImage(val uri: Uri) : CreateEventEvent

    data class RemoveDeletingImage(val uri: Uri) : CreateEventEvent

    data object HideShackbar : CreateEventEvent
}
