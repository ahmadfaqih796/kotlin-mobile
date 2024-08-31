package com.project.latihan.model.api.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseClient {
    private const val BASE_URL = "https://api-dev.dikahadir.com" // Ganti dengan base URL API Anda

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}