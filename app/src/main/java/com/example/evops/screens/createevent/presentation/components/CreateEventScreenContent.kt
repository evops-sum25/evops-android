package com.example.evops.screens.createevent.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import com.example.evops.screens.createevent.presentation.components.images.SelectImagesField
import com.example.evops.screens.createevent.presentation.components.tags.FoundTags
import com.example.evops.screens.createevent.presentation.components.tags.SearchTagTextField
import com.example.evops.screens.createevent.presentation.components.tags.SelectedTags
import com.example.evops.screens.createevent.presentation.components.text.DescriptionTextField
import com.example.evops.screens.createevent.presentation.components.text.TitleTextField
import com.example.evops.screens.createevent.presentation.states.CreateEventState

@Composable
fun CreateEventScreenContent(
    formState: CreateEventState,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .verticalScroll(state = rememberScrollState())
                .padding(horizontal = 12.dp)
                .fillMaxSize(),
    ) {
        SelectImagesField(
            selectedUris = formState.selectedUris,
            canAddMoreImages = formState.canAddMoreImages,
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth(),
        )
        TitleTextField(title = formState.title, onEvent = onEvent)
        DescriptionTextField(description = formState.description, onEvent = onEvent)
        SearchTagTextField(tagName = formState.searchingTagName, onEvent = onEvent)
        FoundTags(
            foundTags = formState.foundTags,
            selectedTags = formState.selectedTags,
            onEvent = onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        SelectedTags(selectedTags = formState.selectedTags, onEvent = onEvent)
    }
}
