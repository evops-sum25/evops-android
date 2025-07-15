package com.example.evops.core.presentation.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun EventTag(name: String, onClick: (() -> Unit)?, modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable(enabled = onClick != null) { onClick?.invoke() }
                .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
    }
}
