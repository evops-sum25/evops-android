package com.example.evops.screens.createevent.presentation

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateEventState(
    val title: String = "",
    val description: String = "",
    val withAttendance: Boolean = false,
    val isImagePickerOpened: Boolean = false,
    val selectedUris: List<Uri> = emptyList(),
    val deletingUris: List<Uri> = emptyList(),
    val isSnackbarShown: Boolean = false,
) : Parcelable {
    val maxSelectableItems
        get() = (10 - selectedUris.size).takeIf { it >= 0 } ?: 0

    val canAddMoreImages
        get() = maxSelectableItems > 0
}
