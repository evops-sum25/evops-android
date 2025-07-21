package com.example.evops.screens.createevent.data.mappers

import com.example.evops.core.data.model.tag.TagDto
import com.example.evops.screens.createevent.data.model.author.CreateAuthorFormDto
import com.example.evops.screens.createevent.data.model.author.CreateAuthorFormWrapperDto
import com.example.evops.screens.createevent.data.model.event.CreateEventFormDto
import com.example.evops.screens.createevent.data.model.event.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.data.model.tag.CreateTagFormDto
import com.example.evops.screens.createevent.data.model.tag.CreateTagFormWrapperDto
import com.example.evops.screens.createevent.data.model.tag.DescriptionWrapperDto
import com.example.evops.screens.createevent.data.model.tag.TagListDto
import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateEventTag
import com.example.evops.screens.createevent.domain.model.CreateTagForm

object CreateEventMapper {
    fun CreateEventForm.toData() =
        CreateEventFormWrapperDto(
            eventForm =
                CreateEventFormDto(
                    description = this.description,
                    tagIds = this.tagIds,
                    title = this.title,
                )
        )

    fun CreateAuthorForm.toData() =
        CreateAuthorFormWrapperDto(form = CreateAuthorFormDto(name = this.name))

    fun CreateTagForm.toData() =
        CreateTagFormWrapperDto(form = CreateTagFormDto(name = this.name, aliases = this.aliases))

    fun TagListDto.toDomain() = this.tags.map { it.toDomain() }

    fun TagDto.toDomain() = CreateEventTag(id = this.id, name = this.name)

    fun String.suggestTagsByDescription() = DescriptionWrapperDto(description = this)
}
