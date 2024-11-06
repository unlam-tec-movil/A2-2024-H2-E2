package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.ProfileResponse
import retrofit2.http.GET

interface ProfileApi {
    @GET("/api/v1/me/profile")
    suspend fun getProfile(): ProfileResponse
}
