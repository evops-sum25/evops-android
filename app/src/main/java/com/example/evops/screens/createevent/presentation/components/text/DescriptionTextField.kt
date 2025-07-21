package com.example.evops.screens.createevent.presentation.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent
import uniffi.evops.ValidateEventDescriptionResult
import uniffi.evops.getEventDescriptionLenCharMin
import uniffi.evops.getEventTitleLenCharMax
import uniffi.evops.validateEventDescription
import kotlin.math.max

@Composable
fun DescriptionTextField(
    description: String,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(description)) }
    val descriptionValidationResult by remember {
        derivedStateOf {
            if (textFieldValue.text.isEmpty()) ValidateEventDescriptionResult.OK
            else validateEventDescription(textFieldValue.text)
        }
    }

    LaunchedEffect(description) {
        if (description != textFieldValue.text) {
            textFieldValue =
                TextFieldValue(text = description, selection = TextRange(description.length))
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(CreateEventEvent.UpdateDescription(it.text))
        },
        label = { DescriptionLabel() },
        suffix = { SuggestTagsButton(onEvent = onEvent) },
        minLines = 3,
        maxLines = 3,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = descriptionValidationResult != ValidateEventDescriptionResult.OK,
        supportingText = { ValidationMessage(descriptionValidationResult) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun SuggestTagsButton(onEvent: (CreateEventEvent) -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { onEvent(CreateEventEvent.SuggestTagsByDescription) },
        modifier = modifier,
    ) {
        Icon(painter = painterResource(R.drawable.wand_stars), contentDescription = null)
    }
}

@Composable
private fun ValidationMessage(result: ValidateEventDescriptionResult) {
    val message =
        when (result) {
            ValidateEventDescriptionResult.OK -> null
            ValidateEventDescriptionResult.LEN_CHAR_MIN_VIOLATED ->
                stringResource(R.string.description_min_len_error, getEventDescriptionLenCharMin())
            ValidateEventDescriptionResult.LEN_CHAR_MAX_VIOLATED ->
                stringResource(R.string.description_max_len_error, getEventTitleLenCharMax())
        }
    message?.let { Text(text = message) }
}

@Composable
private fun DescriptionLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.description), modifier = modifier)
}
