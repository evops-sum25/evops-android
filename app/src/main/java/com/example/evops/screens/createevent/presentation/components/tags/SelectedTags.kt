package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.evops.R
import com.example.evops.core.presentation.components.tag.EventTag
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import com.example.evops.screens.createevent.presentation.states.UiTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedTags(
    selectedTags: List<UiTag>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.selected_tags), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(12.dp))
        if (selectedTags.isNotEmpty()) {
            SelectedTagsFlowRow(
                selectedTags = selectedTags,
                onEvent = onEvent,
            )
        } else {
            Text(
                stringResource(R.string.try_to_add_some_tags),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                style = LocalTextStyle.current.copy(fontStyle = FontStyle.Italic),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectedTagsFlowRow(
    selectedTags: List<UiTag>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        selectedTags.forEach { tag ->
            EventTag(name = tag.name, onClick = { onEvent(CreateEventEvent.UnselectTag(tag)) })
        }
    }
}
