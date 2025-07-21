package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun SearchTagTextField(
    tagName: String,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(tagName)) }

    LaunchedEffect(tagName) {
        if (tagName != textFieldValue.text) {
            textFieldValue = TextFieldValue(text = tagName, selection = TextRange(tagName.length))
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(CreateEventEvent.UpdateSearchingTagName(it.text))
        },
        label = { SearchTagLabel() },
        suffix = { SearchButton(onEvent = onEvent) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun SearchTagLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.tag_name), modifier = modifier)
}

@Composable
private fun SearchButton(onEvent: (CreateEventEvent) -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = { onEvent(CreateEventEvent.SearchTags) }, modifier = modifier) {
        Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
    }
}
