package com.example.evops.screens.createevent.domain.usecases

import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateUserForm
import com.example.evops.screens.createevent.domain.repository.CreateEventNetworkRepository
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    private val createEventNetworkRepository: CreateEventNetworkRepository
) {
    suspend operator fun invoke(eventForm: CreateEventForm) {
        val userId = createEventNetworkRepository.getUserIds().lastOrNull()
        userId?.let {
            createEventNetworkRepository.createEvent(eventForm, userId)
            return
        }
        createEventNetworkRepository.createUser(CreateUserForm(name = "Asqar Arslanov"))
        this.invoke(eventForm)
    }
}