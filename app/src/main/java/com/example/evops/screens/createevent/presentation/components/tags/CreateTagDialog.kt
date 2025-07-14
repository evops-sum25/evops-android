package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.window.Dialog
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import com.example.evops.screens.createevent.presentation.CreateTagState

@Composable
fun CreateTagDialog(
    tagFormState: CreateTagState,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = { onEvent(CreateEventEvent.DropAddTagForm) }) {
        Column(modifier = modifier) {
            CreateTagTitle()
            TagNameTextField(tagName = tagFormState.name, onEvent = onEvent)
            SubmitTagButton(onEvent = onEvent)
        }
    }
}

@Composable
private fun CreateTagTitle(modifier: Modifier = Modifier) {
    Text(stringResource(R.string.create_tag), modifier = modifier)
}

@Composable
private fun TagNameTextField(
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
            onEvent(CreateEventEvent.UpdateTagName(it.text))
        },
        label = { SearchTagLabel() },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun SubmitTagButton(onEvent: (CreateEventEvent) -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = { onEvent(CreateEventEvent.SubmitTag) }, modifier = modifier) {
        Text(stringResource(R.string.submit_tag))
    }
}

@Composable
private fun SearchTagLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.tag_name), modifier = modifier)
}
