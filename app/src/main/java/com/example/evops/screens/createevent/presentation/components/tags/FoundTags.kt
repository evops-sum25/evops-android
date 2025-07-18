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
fun FoundTags(
    foundTags: List<UiTag>,
    selectedTags: List<UiTag>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.found_tags), modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(12.dp))
        if (foundTags.isNotEmpty()) {
            FoundTagsFlowRow(
                foundTags = foundTags,
                selectedTags = selectedTags,
                onEvent = onEvent,
            )
        } else {
            Text(
                stringResource(R.string.tags_are_not_found_try_to_search),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                style = LocalTextStyle.current.copy(fontStyle = FontStyle.Italic),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FoundTagsFlowRow(
    foundTags: List<UiTag>,
    selectedTags: List<UiTag>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        foundTags.forEach { tag ->
            val (onClick, color) =
                if (selectedTags.contains(tag)) {
                    Pair(null, MaterialTheme.colorScheme.secondaryContainer)
                } else {
                    Pair(
                        { onEvent(CreateEventEvent.SelectTag(tag)) },
                        MaterialTheme.colorScheme.primaryContainer,
                    )
                }
            EventTag(name = tag.name, onClick = onClick, color = color)
        }
    }
}
