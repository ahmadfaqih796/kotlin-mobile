package com.project.latihan.controller

import android.util.Log
import com.project.latihan.model.api.client.BaseClient
import com.project.latihan.model.api.service.AuthService
import com.project.latihan.model.datastore.UserPreferencesDataStore
import com.project.latihan.model.entities.UserResponse

class AuthController(
    private val userPreferencesDataStore: UserPreferencesDataStore
) {
    private val authClient = BaseClient.retrofit.create(AuthService::class.java)

    suspend fun login(email: String, password: String): UserResponse {
        try {
            val response = authClient.login(email, password, "local")
            if (response.isSuccessful) {
                val userResponse = response.body()
                userResponse?.let {
                    userPreferencesDataStore.saveUserToken(it.accessToken)
                    return it
                } ?: throw Exception("No response body")
            } else {
                val errorResponse = response.errorBody()?.string()
                throw Exception(errorResponse)
            }
        } catch (e: Exception) {
            throw Exception("Network error: ${e.message}")
        }
    }
    val userToken = userPreferencesDataStore.userToken
}