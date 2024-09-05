package com.project.latihan.model.api.service

import com.project.latihan.model.entities.TrackingResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TrackingService {
    @FormUrlEncoded
    @POST("/hadir-service/last-tracking")
    suspend fun post(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("strategy") strategy: String
    ): Response<TrackingResponse>
}