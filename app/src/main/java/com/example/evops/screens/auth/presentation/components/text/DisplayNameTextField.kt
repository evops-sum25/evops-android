package com.example.evops.screens.auth.presentation.components.text

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
import com.example.evops.screens.auth.presentation.AuthEvent
import uniffi.evops.ValidateUserDisplayNameResult
import uniffi.evops.getUserDisplayNameLenCharMax
import uniffi.evops.getUserDisplayNameLenCharMin
import uniffi.evops.validateUserDisplayName

@Composable
fun DisplayNameTextField(
    displayName: String,
    onEvent: (AuthEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(displayName)) }
    val displayNameValidationResult by remember {
        derivedStateOf {
            if (textFieldValue.text.isEmpty()) ValidateUserDisplayNameResult.OK
            else validateUserDisplayName(textFieldValue.text)
        }
    }

    LaunchedEffect(displayName) {
        if (displayName != textFieldValue.text) {
            textFieldValue =
                TextFieldValue(text = displayName, selection = TextRange(displayName.length))
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(AuthEvent.UpdateDisplayName(it.text))
        },
        label = { DisplayNameLabel() },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = displayNameValidationResult != ValidateUserDisplayNameResult.OK,
        supportingText = { ValidationMessage(displayNameValidationResult) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun ValidationMessage(result: ValidateUserDisplayNameResult) {
    val message =
        when (result) {
            ValidateUserDisplayNameResult.OK -> null
            ValidateUserDisplayNameResult.LEN_CHAR_MIN_VIOLATED ->
                stringResource(R.string.display_name_min_len_error, getUserDisplayNameLenCharMin())
            ValidateUserDisplayNameResult.LEN_CHAR_MAX_VIOLATED ->
                stringResource(R.string.display_name_max_len_error, getUserDisplayNameLenCharMax())
        }
    message?.let { Text(text = message) }
}

@Composable
private fun DisplayNameLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.display_name), modifier = modifier)
}
