package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import com.example.evops.screens.createevent.presentation.states.CreateTagState
import uniffi.evops.ValidateTagNameResult
import uniffi.evops.validateTagName

@Composable
fun CreateTagDialog(
    tagFormState: CreateTagState,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = { onEvent(CreateEventEvent.DropAddTagForm) }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier =
                modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(18.dp),
        ) {
            SuggestedTagsTitle()
            TagNameTextField(tagName = tagFormState.name, onEvent = onEvent)
            DialogButtons(onEvent = onEvent, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun SuggestedTagsTitle(modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.create_tag_title),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
    )
}

@Composable
private fun TagNameTextField(
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
            onEvent(CreateEventEvent.UpdateTagName(it.text))
        },
        label = { SearchTagLabel() },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        isError = tagValidationResult != ValidateTagNameResult.OK,
        supportingText = { TagValidationMessage(tagValidationResult) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun DialogButtons(onEvent: (CreateEventEvent) -> Unit, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.End, modifier = modifier) {
        TextButton(onClick = { onEvent(CreateEventEvent.DropAddTagForm) }) {
            Text(stringResource(R.string.cancel))
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = { onEvent(CreateEventEvent.SubmitTag) }) {
            Text(stringResource(R.string.submit))
        }
    }
}

@Composable
private fun SearchTagLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.tag_name), modifier = modifier)
}
