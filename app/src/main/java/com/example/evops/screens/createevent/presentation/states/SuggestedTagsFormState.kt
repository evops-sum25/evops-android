package com.example.evops.screens.createevent.presentation.states

data class SuggestedTagsFormState(
    val isLoading: Boolean = false,
    val suggestedTags: List<UiTag> = emptyList(),
    val message: String = "",
)
