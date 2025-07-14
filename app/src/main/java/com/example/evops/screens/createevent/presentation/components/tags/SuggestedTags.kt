package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.core.presentation.components.tag.EventTag
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import com.example.evops.screens.createevent.presentation.UiTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestedTags(
    tags: List<UiTag>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        tags.forEach { tag ->
            EventTag(name = tag.name, onClick = { onEvent(CreateEventEvent.SelectTag(tag)) })
        }
    }
}
