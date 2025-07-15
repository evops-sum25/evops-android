package com.example.evops.screens.createevent.domain.usecases

import com.example.evops.core.common.Resource
import com.example.evops.screens.createevent.domain.model.CreateEventTag
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTagsUseCase
@Inject
constructor(private val createEventNetworkRepository: CreateEventNetworkRepository) {
    operator fun invoke(tagName: String): Flow<Resource<List<CreateEventTag>>> = flow {
        try {
            emit(Resource.Loading())
            val tags = createEventNetworkRepository.getTags(tagName)
            emit(Resource.Success(tags))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}
