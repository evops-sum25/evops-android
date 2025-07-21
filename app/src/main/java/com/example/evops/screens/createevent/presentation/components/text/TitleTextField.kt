package com.example.evops.screens.createevent.presentation.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import uniffi.evops.ValidateEventTitleResult
import uniffi.evops.getEventTitleLenCharMax
import uniffi.evops.getEventTitleLenCharMin
import uniffi.evops.validateEventTitle

@Composable
fun TitleTextField(
    title: String,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(title)) }
    val titleValidationResult by remember {
        derivedStateOf {
            if (textFieldValue.text.isEmpty()) ValidateEventTitleResult.OK
            else validateEventTitle(textFieldValue.text)
        }
    }

    LaunchedEffect(title) {
        if (title != textFieldValue.text) {
            textFieldValue = TextFieldValue(text = title, selection = TextRange(title.length))
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(CreateEventEvent.UpdateTitle(it.text))
        },
        label = { TitleLabel() },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = titleValidationResult != ValidateEventTitleResult.OK,
        supportingText = { ValidationMessage(titleValidationResult) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun ValidationMessage(result: ValidateEventTitleResult) {
    val message =
        when (result) {
            ValidateEventTitleResult.OK -> null
            ValidateEventTitleResult.LEN_CHAR_MIN_VIOLATED ->
                stringResource(R.string.title_min_len_error, getEventTitleLenCharMin())
            ValidateEventTitleResult.LEN_CHAR_MAX_VIOLATED ->
                stringResource(R.string.title_max_len_error, getEventTitleLenCharMax())
        }
    message?.let { Text(text = message) }
}

@Composable
private fun TitleLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.title), modifier = modifier)
}
