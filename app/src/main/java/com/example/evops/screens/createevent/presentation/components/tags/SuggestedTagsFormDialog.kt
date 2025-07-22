package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.evops.R
import com.example.evops.core.presentation.components.tag.EventTag
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import com.example.evops.screens.createevent.presentation.states.SuggestedTagsFormState
import com.example.evops.screens.createevent.presentation.states.UiTag

@Composable
fun SuggestedTagsFormDialog(
    suggestedTagsFormState: SuggestedTagsFormState,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = { onEvent(CreateEventEvent.DropSuggestedTagsForm) }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier =
                modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(18.dp),
        ) {
            SuggestedTagsTitle()
            DialogContent(
                suggestedTagsFormState = suggestedTagsFormState,
                modifier = Modifier.aspectRatio(4 / 3f).padding(vertical = 12.dp),
            )
            DialogButtons(
                onEvent = onEvent,
                isAddButtonEnabled = suggestedTagsFormState.suggestedTags.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun SuggestedTagsTitle(modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.suggested_tags),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SuggestedTagsFlowRow(suggestedTags: List<UiTag>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            suggestedTags.forEach { tag -> EventTag(name = tag.name, onClick = null) }
        }
    }
}

@Composable
fun DialogContent(suggestedTagsFormState: SuggestedTagsFormState, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        if (suggestedTagsFormState.isLoading) {
            CircularProgressIndicator(
                strokeWidth = 6.dp,
                modifier =
                    Modifier.fillMaxHeight()
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .align(Alignment.Center),
            )
        } else if (suggestedTagsFormState.suggestedTags.isEmpty()) {
            Text(stringResource(R.string.no_suggested_tags))
        } else {
            SuggestedTagsFlowRow(suggestedTags = suggestedTagsFormState.suggestedTags)
        }
    }
}

@Composable
private fun DialogButtons(
    isAddButtonEnabled: Boolean,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(horizontalArrangement = Arrangement.End, modifier = modifier) {
        TextButton(onClick = { onEvent(CreateEventEvent.DropSuggestedTagsForm) }) {
            Text(stringResource(R.string.cancel))
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
                onEvent(CreateEventEvent.AddSuggestedTags)
                onEvent(CreateEventEvent.DropSuggestedTagsForm)
            },
            enabled = isAddButtonEnabled,
        ) {
            Text(stringResource(R.string.add))
        }
    }
}
