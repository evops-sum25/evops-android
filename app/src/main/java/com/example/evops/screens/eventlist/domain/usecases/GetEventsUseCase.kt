package com.example.evops.screens.eventlist.domain.usecases

import com.example.evops.core.common.Result
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.repository.EventListNetworkRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventListNetworkRepository: EventListNetworkRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Result.Loading<EventItem>())
            val events = eventListNetworkRepository.getEvents()
            emit(Result.Success(events))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Result.Error("Could not reach server. Check your Internet connection"))
        }
    }
}