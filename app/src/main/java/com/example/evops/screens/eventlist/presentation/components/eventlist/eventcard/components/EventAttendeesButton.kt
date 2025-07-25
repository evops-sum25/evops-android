package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

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
import com.example.evops.R

@Composable
fun EventAttendeesButton(attendeesCount: UInt, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(onClick = {}), // TODO("add on click")
    ) {
        AttendIcon(isAttending = false, modifier = Modifier) // TODO("add is attending")

        Text(text = attendeesCount.toString())
    }
}

@Composable
private fun AttendIcon(isAttending: Boolean, modifier: Modifier = Modifier) {
    val iconVector =
        if (isAttending) {
            Icons.Filled.ThumbUp
        } else {
            Icons.Outlined.ThumbUpOffAlt
        }

    Icon(
        imageVector = iconVector,
        contentDescription = stringResource(R.string.description_disabled_attend_icon),
        modifier = modifier,
    )
}
