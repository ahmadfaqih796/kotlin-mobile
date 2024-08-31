package com.project.latihan.model.api.service

import com.project.latihan.model.entities.UserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("/authentication")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("strategy") strategy: String
    ): Response<UserResponse>
}