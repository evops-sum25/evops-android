package com.example.evops.screens.createevent.presentation

import android.os.Parcelable
import com.example.evops.screens.createevent.domain.model.CreateTagForm
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateTagState(val name: String = "", val aliases: List<String> = emptyList()) :
    Parcelable {
    fun toDomain() = CreateTagForm(name = name, aliases = aliases)
}
