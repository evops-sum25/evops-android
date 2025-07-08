package com.example.evops.screens.createevent.domain.repositories

import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import java.io.File

interface CreateEventNetworkRepository {
    suspend fun getAuthorIds(): List<String>

    suspend fun createAuthor(userForm: CreateAuthorForm)

    suspend fun createEvent(eventForm: CreateEventForm, userId: String): String

    suspend fun postImage(eventId: String, image: File)
}
