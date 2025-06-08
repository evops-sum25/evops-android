package com.example.evops.screens.eventlist.presentation.components.eventcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUpOffAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.R
import com.example.evops.screens.eventlist.presentation.components.PreviewData

@Composable
fun EventAttendees(attendeesCount: UInt, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AttendButton(onClick = {}, modifier = Modifier) // TODO("implement on click")

        Text(text = attendeesCount.toString())
    }
}

@Composable
private fun AttendButton(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Outlined.ThumbUpOffAlt,
            contentDescription = stringResource(R.string.description_disabled_attend_icon)
        )
    }
}

@Preview
@Composable
private fun EventAttendeesPreview() {
    EventAttendees(attendeesCount = PreviewData.eventData.attendeesCount)
}