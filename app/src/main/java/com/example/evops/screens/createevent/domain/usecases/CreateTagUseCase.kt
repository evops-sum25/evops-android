package com.example.evops.screens.createevent.domain.usecases

import com.example.evops.core.common.Resource
import com.example.evops.screens.createevent.domain.model.CreateTagForm
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CreateTagUseCase
@Inject
constructor(private val createEventNetworkRepository: CreateEventNetworkRepository) {
    operator fun invoke(tagForm: CreateTagForm): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val tagId = createEventNetworkRepository.createTag(tagForm)
            tagId?.let { emit(Resource.Success(tagId)) }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Http error occurred"))
        } catch (_: IOException) {
            emit(Resource.Error("Could not reach server. Check your Internet connection"))
        }
    }
}
