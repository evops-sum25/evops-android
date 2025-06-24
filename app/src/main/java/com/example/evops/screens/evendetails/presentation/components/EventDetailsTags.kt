package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evops.screens.PreviewData
import com.example.evops.screens.evendetails.domain.model.EventDetailsTag

@Composable
fun EventDetailsTags(tagsData: List<EventDetailsTag>, modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        tagsData.forEach { tagData ->
            EventDetailsTag(eventDetailsTag = tagData)
        }
    }
}

@Composable
private fun EventDetailsTag(eventDetailsTag: EventDetailsTag, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(text = eventDetailsTag.name)
    }
}

@Preview
@Composable
private fun EventDetailsTagsPreview() {
    EventDetailsTags(tagsData = PreviewData.eventDetails.tagsData)
}