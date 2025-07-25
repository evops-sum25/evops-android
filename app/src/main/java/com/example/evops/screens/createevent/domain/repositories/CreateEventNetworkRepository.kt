package com.example.evops.screens.createevent.domain.repositories

import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateEventTag
import com.example.evops.screens.createevent.domain.model.CreateTagForm
import java.io.File

interface CreateEventNetworkRepository {

    suspend fun createEvent(eventForm: CreateEventForm): String?

    suspend fun postImage(eventId: String, image: File): String?

    suspend fun getTags(name: String): List<CreateEventTag>

    suspend fun getTag(tagId: String): CreateEventTag

    suspend fun createTag(tagForm: CreateTagForm): String?

    suspend fun suggestTagsByDescription(description: String): List<CreateEventTag>
}
