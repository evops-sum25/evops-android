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
import uniffi.evops.ValidateUserLoginResult
import uniffi.evops.getUserLoginLenCharMax
import uniffi.evops.getUserLoginLenCharMin
import uniffi.evops.validateUserLogin

@Composable
fun LoginTextField(login: String, onEvent: (AuthEvent) -> Unit, modifier: Modifier = Modifier) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(login)) }
    val loginValidationResult by remember {
        derivedStateOf {
            if (textFieldValue.text.isEmpty()) ValidateUserLoginResult.OK
            else validateUserLogin(textFieldValue.text)
        }
    }

    LaunchedEffect(login) {
        if (login != textFieldValue.text) {
            textFieldValue = TextFieldValue(text = login, selection = TextRange(login.length))
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(AuthEvent.UpdateLogin(it.text))
        },
        label = { LoginLabel() },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        isError = loginValidationResult != ValidateUserLoginResult.OK,
        supportingText = { ValidationMessage(loginValidationResult) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun ValidationMessage(result: ValidateUserLoginResult) {
    val message =
        when (result) {
            ValidateUserLoginResult.OK -> null
            ValidateUserLoginResult.LEN_CHAR_MIN_VIOLATED ->
                stringResource(R.string.login_min_len_error, getUserLoginLenCharMin())
            ValidateUserLoginResult.LEN_CHAR_MAX_VIOLATED ->
                stringResource(R.string.login_max_len_error, getUserLoginLenCharMax())
            ValidateUserLoginResult.REGEX_VIOLATED ->
                stringResource(R.string.invalid_login_format_error)
        }
    message?.let { Text(text = message) }
}

@Composable
private fun LoginLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.login), modifier = modifier)
}
