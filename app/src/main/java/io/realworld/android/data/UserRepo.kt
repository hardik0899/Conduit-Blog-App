package io.realworld.android.data

import io.realworld.api.ConduitClient
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.entities.User
import io.realworld.api.models.requests.LoginRequest

object UserRepo {

    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun login(email: String, password: String): User? {
        val response = api.loginUser(LoginRequest(LoginData(email, password)))

        // TODO: Save it in SharedPreferences
        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user
    }

    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
        return authAPI.getCurrentUser().body()?.user
    }

    suspend fun getUserProfile() = authAPI.getCurrentUser().body()?.user
}