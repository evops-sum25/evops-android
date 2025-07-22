package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.core.presentation.components.tag.EventTag
import com.example.evops.screens.eventlist.domain.model.EventItemTag
import com.example.evops.screens.eventlist.presentation.EventListEvent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventTags(
    tagsData: List<EventItemTag>,
    searchTagIds: List<String>,
    onEvent: (EventListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        tagsData.forEach { tagData ->
            val (onTagClick, color) =
                if (searchTagIds.contains(tagData.id)) {
                    Pair(
                        { onEvent(EventListEvent.RemoveSearchTag(tagData.id)) },
                        MaterialTheme.colorScheme.primaryContainer,
                    )
                } else {
                    Pair(
                        { onEvent(EventListEvent.AddSearchTag(tagData.id)) },
                        MaterialTheme.colorScheme.secondaryContainer,
                    )
                }
            EventTag(
                name = tagData.name,
                onClick = {
                    onTagClick()
                    onEvent(EventListEvent.LoadFirstEvents)
                },
                color = color,
            )
        }
    }
}
