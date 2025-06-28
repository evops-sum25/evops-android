package com.example.evops.screens.createevent.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun WithAttendanceSwitch(
    withAttendance: Boolean,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(text = "With Attendance:")

        Spacer(modifier = Modifier.size(8.dp))

        Switch(
            checked = withAttendance,
            onCheckedChange = { onEvent(CreateEventEvent.UpdateWithAttendance(it)) },
        )
    }
}
