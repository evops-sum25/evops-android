package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.core.presentation.components.tag.EventTag
import com.example.evops.screens.evendetails.domain.model.EventDetailsTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventDetailsTags(tagsData: List<EventDetailsTag>, modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        tagsData.forEach { tagData -> EventTag(name = tagData.name, onClick = null) }
    }
}
