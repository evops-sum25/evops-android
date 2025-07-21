package com.example.evops.screens.createevent.presentation

import android.net.Uri
import com.example.evops.screens.createevent.presentation.states.UiTag

sealed interface CreateEventEvent {
    data class UpdateTitle(val title: String) : CreateEventEvent

    data class UpdateDescription(val description: String) : CreateEventEvent

    data object SubmitEvent : CreateEventEvent

    data class OpenHideImagePicker(val shouldOpen: Boolean) : CreateEventEvent

    data class AddImages(val uris: List<Uri>) : CreateEventEvent

    data class DeleteImage(val imageId: Int) : CreateEventEvent

    data object HideShackbar : CreateEventEvent

    data class UpdateSearchingTagName(val name: String) : CreateEventEvent

    data class OpenHideAddTagForm(val shouldOpen: Boolean) : CreateEventEvent

    data object SearchTags : CreateEventEvent

    data class UpdateTagName(val name: String) : CreateEventEvent

    data object SubmitTag : CreateEventEvent

    data class SelectTag(val tag: UiTag) : CreateEventEvent

    data class UnselectTag(val tag: UiTag) : CreateEventEvent

    data object DropAddTagForm : CreateEventEvent

    data object SuggestTagsByDescription : CreateEventEvent

    data object AddSuggestedTags : CreateEventEvent

    data object DropSuggestedTagsForm : CreateEventEvent
}
