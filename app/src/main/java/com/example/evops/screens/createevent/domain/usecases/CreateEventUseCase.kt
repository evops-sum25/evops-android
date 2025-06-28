package com.example.evops.screens.createevent.domain.usecases

import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import javax.inject.Inject

class CreateEventUseCase
    @Inject
    constructor(
        private val createEventNetworkRepository: CreateEventNetworkRepository,
    ) {
        suspend operator fun invoke(eventForm: CreateEventForm) {
            val userId = createEventNetworkRepository.getAuthorIds().lastOrNull()
            userId?.let {
                createEventNetworkRepository.createEvent(eventForm, userId)
                return
            }
            createEventNetworkRepository.createAuthor(CreateAuthorForm(name = "Asqar Arslanov"))
            this.invoke(eventForm)
        }
    }
