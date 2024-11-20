package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.request.EditProfileRequest
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.ProfileResponse
import ar.edu.unlam.mobile.scaffolding.data.remote.util.ApiConfig
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {
    @GET(ApiConfig.Endpoints.PROFILE)
    suspend fun getProfile(): ProfileResponse

    @PUT(ApiConfig.Endpoints.PROFILE)
    suspend fun updateProfile(
        @Body profileInfo: EditProfileRequest,
    ): ProfileResponse
}
