package com.example.evops.screens.createevent.presentation.components.tags

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.evops.R
import uniffi.evops.ValidateTagNameResult
import uniffi.evops.getTagNameLenCharMax
import uniffi.evops.getTagNameLenCharMin

@Composable
fun TagValidationMessage(result: ValidateTagNameResult) {
    val message =
        when (result) {
            ValidateTagNameResult.OK -> null
            ValidateTagNameResult.LEN_CHAR_MIN_VIOLATED ->
                stringResource(R.string.tag_name_min_len_error, getTagNameLenCharMin())
            ValidateTagNameResult.LEN_CHAR_MAX_VIOLATED ->
                stringResource(R.string.tag_name_max_len_error, getTagNameLenCharMax())
            ValidateTagNameResult.REGEX_VIOLATED ->
                stringResource(R.string.invalid_tag_name_format_error)
        }
    message?.let { Text(text = message) }
}
