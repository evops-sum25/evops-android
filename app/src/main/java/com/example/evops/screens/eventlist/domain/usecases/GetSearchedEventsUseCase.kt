package com.example.evops.screens.eventlist.domain.usecases

import com.example.evops.core.common.Resource
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.repositories.EventListNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchedEventsUseCase
@Inject
constructor(private val eventListNetworkRepository: EventListNetworkRepository) {
    operator fun invoke(
        searchString: String,
        lastEventId: String? = null,
    ): Flow<Resource<List<EventItem>>> = flow {
        try {
            emit(Resource.Loading())
            val events =
                eventListNetworkRepository.getSearchedEvents(
                    lastEventId = lastEventId,
                    searchString = searchString,
                )
            emit(Resource.Success(events))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}
