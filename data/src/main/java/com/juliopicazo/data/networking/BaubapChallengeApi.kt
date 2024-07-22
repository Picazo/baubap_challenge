package com.juliopicazo.data.networking

import com.juliopicazo.data.networking.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface BaubapChallengeApi {
    @GET("api/login")
    suspend fun login(): Response<LoginResponse>
}
