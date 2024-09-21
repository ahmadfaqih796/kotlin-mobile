package com.project.latihan.controller

import android.util.Log
import com.google.gson.Gson
import com.project.latihan.model.api.client.BaseClient
import com.project.latihan.model.api.service.AuthService
import com.project.latihan.model.datastore.UserPreferencesDataStore
import com.project.latihan.model.entities.ErrorResponse
import com.project.latihan.model.entities.UserResponse

class AuthController(
    private val userPreferencesDataStore: UserPreferencesDataStore
) {
    private val authClient = BaseClient.retrofit.create(AuthService::class.java)
    private val gson = Gson()

    suspend fun login(email: String, password: String): UserResponse {
        try {
            val response = authClient.login(email, password, "local")
            if (response.isSuccessful) {
                val userResponse = response.body()
                Log.d("ssssssssss", response.body().toString())
                userResponse?.let {
                    userPreferencesDataStore.saveUserToken(it.accessToken)
                    return it
                } ?: throw Exception("No response body")
            } else {
                val errorResponseString = response.errorBody()?.string()
                val errorResponse = gson.fromJson(errorResponseString, ErrorResponse::class.java)
                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
//            throw Exception("Network error: ${e.message}")
            throw Exception(e.message)
        }
    }

    val userToken = userPreferencesDataStore.userToken
}