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
import com.example.evops.screens.createevent.presentation.CreateEventState
import com.example.evops.screens.createevent.presentation.components.buttons.WithAttendanceSwitch
import com.example.evops.screens.createevent.presentation.components.images.SelectImagesField
import com.example.evops.screens.createevent.presentation.components.text.DescriptionTextField
import com.example.evops.screens.createevent.presentation.components.text.TitleTextField

@Composable
fun CreateEventScreenContent(
    formState: CreateEventState,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
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
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        )
        TitleTextField(
            title = formState.title,
            onEvent = onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        DescriptionTextField(
            description = formState.description,
            onEvent = onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
        WithAttendanceSwitch(
            withAttendance = formState.withAttendance,
            onEvent = onEvent,
            modifier = Modifier.padding(vertical = 4.dp),
        )
    }
}
