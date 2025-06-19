package com.example.evops.screens.eventlist.domain.usecases

import com.example.evops.screens.eventlist.domain.repository.EventListNetworkRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventUseCase @Inject constructor(
    private val eventListNetworkRepository: EventListNetworkRepository
) {
    operator fun invoke() = flow {

    }
}