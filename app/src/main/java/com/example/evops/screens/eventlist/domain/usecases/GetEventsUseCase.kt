package com.example.evops.screens.eventlist.domain.usecases

import com.example.evops.core.common.Resource
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.repositories.EventListNetworkRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetEventsUseCase
@Inject
constructor(private val eventListNetworkRepository: EventListNetworkRepository) {
    operator fun invoke(
        lastEventId: String? = null,
        searchString: String? = null,
        tagIds: List<String> = emptyList(),
    ): Flow<Resource<List<EventItem>>> = flow {
        try {
            emit(Resource.Loading())
            val events =
                eventListNetworkRepository.getEvents(
                    lastEventId = lastEventId,
                    searchString = searchString,
                    tagIds = tagIds,
                )
            emit(Resource.Success(events))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}
