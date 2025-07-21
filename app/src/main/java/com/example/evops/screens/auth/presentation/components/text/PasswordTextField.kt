package com.example.evops.screens.auth.presentation.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.evops.R
import com.example.evops.screens.auth.presentation.AuthEvent
import uniffi.evops.ValidateUserPasswordResult
import uniffi.evops.getUserPasswordLenCharMax
import uniffi.evops.getUserPasswordLenCharMin
import uniffi.evops.validateUserPassword

@Composable
fun PasswordTextField(
    password: String,
    onEvent: (AuthEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(password)) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val passwordValidationResult by remember {
        derivedStateOf {
            if (textFieldValue.text.isEmpty()) ValidateUserPasswordResult.OK
            else validateUserPassword(textFieldValue.text)
        }
    }

    LaunchedEffect(password) {
        if (password != textFieldValue.text) {
            textFieldValue = TextFieldValue(text = password, selection = TextRange(password.length))
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(AuthEvent.UpdatePassword(it.text))
        },
        label = { PasswordLabel() },
        singleLine = true,
        visualTransformation =
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        isError = passwordValidationResult != ValidateUserPasswordResult.OK,
        supportingText = { ValidationMessage(passwordValidationResult) },
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null)
            }
        },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun ValidationMessage(result: ValidateUserPasswordResult) {
    val message =
        when (result) {
            ValidateUserPasswordResult.OK -> null
            ValidateUserPasswordResult.LEN_CHAR_MIN_VIOLATED ->
                stringResource(R.string.password_min_len_error, getUserPasswordLenCharMin())
            ValidateUserPasswordResult.LEN_CHAR_MAX_VIOLATED ->
                stringResource(R.string.password_max_len_error, getUserPasswordLenCharMax())
            ValidateUserPasswordResult.REGEX_VIOLATED ->
                stringResource(R.string.invalid_password_format_error)
        }
    message?.let { Text(text = message) }
}

@Composable
private fun PasswordLabel(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.password), modifier = modifier)
}
