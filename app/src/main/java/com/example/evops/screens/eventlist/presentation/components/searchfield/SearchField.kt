package com.example.evops.screens.eventlist.presentation.components.searchfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.evops.screens.eventlist.presentation.EventListEvent

@Composable
fun SearchField(
    searchString: String,
    onEvent: (EventListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(searchString)) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(searchString) {
        if (searchString != textFieldValue.text) {
            textFieldValue =
                TextFieldValue(text = searchString, selection = TextRange(searchString.length))
        }
    }

    TextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEvent(EventListEvent.UpdateSearchString(it.text))
        },
        interactionSource = interactionSource,
        shape = RoundedCornerShape(16.dp),
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions =
            KeyboardActions(
                onSearch = {
                    onEvent(EventListEvent.LoadFirstEvents(searchString = textFieldValue.text))
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
        prefix = {
            if (!isFocused && searchString.isBlank()) {
                Icon(Icons.Rounded.Search, contentDescription = null)
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
    )
}
