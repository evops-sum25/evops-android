package com.example.evops.screens.createevent.domain.repository

import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateUserForm

interface CreateEventNetworkRepository {
    suspend fun getUserIds(): List<String>
    suspend fun createUser(userForm: CreateUserForm)
    suspend fun createEvent(eventForm: CreateEventForm, userId: String)
}