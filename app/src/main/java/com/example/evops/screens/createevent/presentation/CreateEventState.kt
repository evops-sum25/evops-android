package com.example.evops.screens.createevent.presentation

import android.net.Uri
import android.os.Parcelable
import com.example.evops.screens.createevent.domain.model.CreateEventTag
import com.example.evops.screens.createevent.domain.model.CreateTagForm
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateEventState(
    val title: String = "",
    val description: String = "",
    val withAttendance: Boolean = false,
    val isImagePickerOpened: Boolean = false,
    val selectedUris: List<Uri> = emptyList(),
    val snackbarMessage: String? = null,
    val searchingTagName: String = "",
    val isAddTagFormOpen: Boolean = false,
    val suggestedTags: List<UiTag> = emptyList(),
    val selectedTags: List<UiTag> = emptyList(),
) : Parcelable {
    val maxSelectableItems
        get() = (10 - selectedUris.size).takeIf { it >= 0 } ?: 0

    val canAddMoreImages
        get() = maxSelectableItems > 0
}

@Parcelize
data class CreateTagState(val name: String = "", val aliases: List<String> = emptyList()) :
    Parcelable {
    fun toDomain() = CreateTagForm(name = name, aliases = aliases)
}

@Parcelize data class UiTag(val id: String, val name: String = "") : Parcelable

fun CreateEventTag.toUi() = UiTag(id = id, name = name)
