package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUpOffAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.R
import com.example.evops.screens.PreviewData

@Composable
fun EventDetailsAttendeesButton(attendeesCount: UInt, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(onClick = {}) // TODO("add on click")
    ) {
        AttendIcon(isAttending = false, modifier = Modifier) // TODO("add is attending")

        Text(text = attendeesCount.toString())
    }
}

@Composable
private fun AttendIcon(
    isAttending: Boolean, modifier: Modifier = Modifier
) {
    val iconVector = if (isAttending) {
        Icons.Filled.ThumbUp
    } else {
        Icons.Outlined.ThumbUpOffAlt
    }

    Icon(
        imageVector = iconVector,
        contentDescription = stringResource(R.string.description_disabled_attend_icon),
        modifier = modifier
    )
}

@Preview
@Composable
private fun EventAttendeesPreview() {
    EventDetailsAttendeesButton(attendeesCount = PreviewData.eventItem.attendeesCount)
}