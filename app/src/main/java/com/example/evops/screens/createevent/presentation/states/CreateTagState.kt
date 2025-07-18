package com.example.evops.screens.createevent.presentation.states

import com.example.evops.screens.createevent.domain.model.CreateTagForm

data class CreateTagState(val name: String = "", val aliases: List<String> = emptyList()) {
    fun toDomain() = CreateTagForm(name = name, aliases = aliases)
}
