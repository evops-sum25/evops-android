package com.example.evops.screens.createevent.data.repositories

import android.util.Log
import com.example.evops.core.common.Config
import com.example.evops.core.data.datastore.AuthDataStore
import com.example.evops.core.domain.repository.AuthRepository
import com.example.evops.screens.createevent.data.api.CreateEventApi
import com.example.evops.screens.createevent.data.mappers.CreateEventMapper.toData
import com.example.evops.screens.createevent.data.mappers.CreateEventMapper.toDomain
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateEventTag
import com.example.evops.screens.createevent.domain.model.CreateTagForm
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File

class CreateEventNetworkRepositoryImpl(
    private val createEventApi: CreateEventApi,
    private val authDataStore: AuthDataStore,
    private val authRepository: AuthRepository,
) : CreateEventNetworkRepository {

    override suspend fun createEvent(eventForm: CreateEventForm): String? {
        return intercept { token ->
                createEventApi.createEvent(
                    formDto = eventForm.toData(),
                    accessToken = Config.constructAccessToken(token),
                )
            }
            ?.eventId
    }

    override suspend fun postImage(eventId: String, image: File): String? {
        val imageMultipart =
            MultipartBody.Part.createFormData(
                name = "image",
                filename = image.name,
                body = image.asRequestBody(),
            )
        return intercept { token ->
                createEventApi.postImage(
                    eventId = eventId,
                    image = imageMultipart,
                    accessToken = Config.constructAccessToken(token),
                )
            }
            ?.imageId
    }

    override suspend fun getTags(name: String): List<CreateEventTag> {
        Log.d("DEB", name)
        return createEventApi.getTags().toDomain().filter { it.name.contains(name) }
    }

    override suspend fun getTag(tagId: String): CreateEventTag {
        return createEventApi.getTag(tagId).tag.toDomain()
    }

    override suspend fun createTag(tagForm: CreateTagForm): String? {
        return intercept { token ->
                createEventApi.createTag(
                    formDto = tagForm.toData(),
                    accessToken = Config.constructAccessToken(token),
                )
            }
            ?.tagId
    }

    override suspend fun suggestTagsByDescription(description: String): List<CreateEventTag> {
        val tagsDto = intercept { token ->
            createEventApi.suggestTagsByDescription(
                description = description,
                accessToken = Config.constructAccessToken(token),
            )
        }
        val tags = tagsDto?.tags?.map { it.toDomain() }
        return tags ?: emptyList()
    }

    private suspend fun <T> intercept(base: suspend (String) -> Response<T>): T? {
        val token = authDataStore.accessToken.filterNotNull().first()
        return token.let {
            val response = base(it)
            if (response.code() == 401) {
                authRepository.refresh()
                val refreshedToken = authDataStore.accessToken.filterNotNull().first()
                base(refreshedToken).body()
            } else {
                response.body()
            }
        }
    }
}
