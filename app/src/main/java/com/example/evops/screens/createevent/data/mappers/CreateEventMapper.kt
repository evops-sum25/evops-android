package com.example.evops.screens.createevent.data.mappers

import com.example.evops.screens.createevent.data.model.author.CreateAuthorFormDto
import com.example.evops.screens.createevent.data.model.author.CreateAuthorFormWrapperDto
import com.example.evops.screens.createevent.data.model.event.CreateEventFormDto
import com.example.evops.screens.createevent.data.model.event.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm

object CreateEventMapper {
    fun CreateEventForm.toData(authorId: String) =
        CreateEventFormWrapperDto(
            eventForm =
                CreateEventFormDto(
                    authorId = authorId,
                    description = this.description,
                    imageUrls = this.imageUrls,
                    tagIds = this.tagIds,
                    title = this.title,
                    withAttendance = this.withAttendance,
                )
        )

    fun CreateAuthorForm.toData() =
        CreateAuthorFormWrapperDto(authorForm = CreateAuthorFormDto(name = this.name))
}
