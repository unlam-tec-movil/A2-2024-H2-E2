package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.ProfileResponse
import ar.edu.unlam.mobile.scaffolding.data.remote.util.ApiConfig
import retrofit2.http.GET

interface ProfileApi {
    @GET(ApiConfig.Endpoints.PROFILE)
    suspend fun getProfile(): ProfileResponse
}
