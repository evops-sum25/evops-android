package com.example.evops.screens.createevent.presentation

import android.net.Uri

sealed interface CreateEventEvent {
    data class UpdateTitle(val title: String) : CreateEventEvent

    data class UpdateDescription(val description: String) : CreateEventEvent

    data class UpdateWithAttendance(val withAttendance: Boolean) : CreateEventEvent

    data object SubmitEvent : CreateEventEvent

    data class OpenHideImagePicker(val shouldOpen: Boolean) : CreateEventEvent

    data class AddImages(val uris: List<Uri>) : CreateEventEvent

    data class DeleteImage(val imageId: Int) : CreateEventEvent

    data object HideShackbar : CreateEventEvent

    data class UpdateSearchingTagName(val name: String) : CreateEventEvent

    data class OpenHideAddTagForm(val shouldOpen: Boolean) : CreateEventEvent

    data object GetTags : CreateEventEvent

    data class UpdateTagName(val name: String) : CreateEventEvent

    data object AddTagAlias : CreateEventEvent

    data class RemoveTagAlias(val tagId: Int) : CreateEventEvent

    data class UpdateTagAlias(val alias: String, val id: Int) : CreateEventEvent

    data object SubmitTag : CreateEventEvent

    data class SelectTag(val tag: UiTag) : CreateEventEvent

    data class UnselectTag(val tag: UiTag) : CreateEventEvent

    data object DropAddTagForm : CreateEventEvent
}
