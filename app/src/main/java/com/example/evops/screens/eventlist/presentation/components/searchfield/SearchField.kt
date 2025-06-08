package com.example.evops.screens.eventlist.presentation.components.searchfield

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(modifier: Modifier = Modifier) {
    TextField(
        value = TextFieldValue(""),
        onValueChange = {},
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        prefix = { Icon(Icons.Rounded.Search, contentDescription = null) },
        suffix = {
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                VerticalDivider(
                    thickness = 2.dp,
                    color = LocalContentColor.current,
                    modifier = Modifier
                )

                Icon(
                    Icons.Rounded.FilterList,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun SearchFieldPreview() {
    SearchField()
}