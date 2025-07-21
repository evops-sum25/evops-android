package com.example.evops.screens.createevent.data.api

import com.example.evops.screens.createevent.data.model.event.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.data.model.event.CreateEventResponse
import com.example.evops.screens.createevent.data.model.image.PostImageResponse
import com.example.evops.screens.createevent.data.model.tag.CreateTagFormWrapperDto
import com.example.evops.screens.createevent.data.model.tag.CreateTagResponse
import com.example.evops.screens.createevent.data.model.tag.DescriptionWrapperDto
import com.example.evops.screens.createevent.data.model.tag.TagIdListDto
import com.example.evops.screens.createevent.data.model.tag.TagListDto
import com.example.evops.screens.createevent.data.model.tag.TagWrapperDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface CreateEventApi {
    @POST("/v1/events")
    suspend fun createEvent(
        @Body formDto: CreateEventFormWrapperDto,
        @Header("Authorization") accessToken: String,
    ): Response<CreateEventResponse>

    @Multipart
    @POST("/v1/events/{event-id}/images")
    suspend fun postImage(
        @Path("event-id") eventId: String,
        @Part image: MultipartBody.Part,
        @Header("Authorization") accessToken: String,
    ): Response<PostImageResponse>

    @GET("/v1/tags") suspend fun getTags(): TagListDto

    @GET("/v1/tags/{tag-id}") suspend fun getTag(@Query("tag-id") tagId: String): TagWrapperDto

    @POST("/v1/tags")
    suspend fun createTag(
        @Body formDto: CreateTagFormWrapperDto,
        @Header("Authorization") accessToken: String,
    ): Response<CreateTagResponse>

    @POST("/v1/tags/suggestions")
    suspend fun suggestTagsByDescription(
        @Body description: DescriptionWrapperDto,
        @Header("Authorization") accessToken: String,
    ): Response<TagIdListDto>
}
