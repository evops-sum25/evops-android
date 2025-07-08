package com.example.evops.screens.createevent.presentation

import android.net.Uri

data class CreateEventState(
    val title: String = "",
    val description: String = "",
    val withAttendance: Boolean = false,
    val isImagePickerOpened: Boolean = false,
    val selectedUris: List<Uri> = emptyList(),
) {
    val maxSelectableItems
        get() = 10 - selectedUris.size
}
