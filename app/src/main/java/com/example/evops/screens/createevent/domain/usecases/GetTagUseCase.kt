package com.example.evops.screens.createevent.domain.usecases

import com.example.evops.core.common.Resource
import com.example.evops.screens.createevent.domain.model.CreateEventTag
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetTagUseCase
@Inject
constructor(private val createEventNetworkRepository: CreateEventNetworkRepository) {
    operator fun invoke(tagId: String): Flow<Resource<CreateEventTag>> = flow {
        try {
            emit(Resource.Loading())
            val tag = createEventNetworkRepository.getTag(tagId)
            emit(Resource.Success(tag))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}
