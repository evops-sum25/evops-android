package com.example.evops.screens.createevent.domain.usecases

import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import java.io.File
import javax.inject.Inject

class CreateEventUseCase
@Inject
constructor(private val createEventNetworkRepository: CreateEventNetworkRepository) {
    suspend operator fun invoke(eventForm: CreateEventForm, images: List<File>): Boolean {
        val eventId = createEventNetworkRepository.createEvent(eventForm)
        eventId?.let {
            images.forEach { image -> createEventNetworkRepository.postImage(eventId, image) }
        }
        return true
    }
}
