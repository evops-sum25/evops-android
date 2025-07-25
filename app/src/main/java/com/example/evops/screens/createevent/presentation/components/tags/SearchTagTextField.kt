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
import androidx.compose.runtime.derivedStateOf
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
import uniffi.evops.ValidateTagNameResult
import uniffi.evops.validateTagName

@Composable
fun SearchTagTextField(
    tagName: String,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(tagName)) }
    val tagValidationResult by remember {
        derivedStateOf {
            if (textFieldValue.text.isEmpty()) ValidateTagNameResult.OK
            else validateTagName(textFieldValue.text)
        }
    }

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
        suffix = {
            SearchButton(
                onEvent = onEvent,
                enabled =
                    tagValidationResult == ValidateTagNameResult.OK &&
                        textFieldValue.text.isNotBlank(),
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        isError = tagValidationResult != ValidateTagNameResult.OK,
        supportingText = { TagValidationMessage(tagValidationResult) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun SearchTagLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.tag_name), modifier = modifier)
}

@Composable
private fun SearchButton(
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    IconButton(
        onClick = { onEvent(CreateEventEvent.SearchTags) },
        enabled = enabled,
        modifier = modifier,
    ) {
        Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
    }
}
