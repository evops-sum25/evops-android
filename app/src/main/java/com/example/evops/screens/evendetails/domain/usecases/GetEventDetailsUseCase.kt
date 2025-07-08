package com.example.evops.screens.evendetails.domain.usecases

import com.example.evops.core.common.Resource
import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.domain.repositories.EventDetailsNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEventDetailsUseCase
@Inject
constructor(private val eventDetailsNetworkRepository: EventDetailsNetworkRepository) {
    operator fun invoke(eventId: String): Flow<Resource<EventDetails>> = flow {
        try {
            emit(Resource.Loading())
            val eventDetails = eventDetailsNetworkRepository.getEventDetails(eventId)
            emit(Resource.Success(eventDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}
