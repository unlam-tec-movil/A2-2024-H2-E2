package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApi {
    @GET("/api/v1/me/profile")
    suspend fun getProfile(): ProfileResponse

    @POST("/api/v1/me/profile/{name}")
    suspend fun updateProfileName(
        @Path("name") newName: String,
    ): ProfileResponse

    @POST("/api/v1/me/profile/{avatarUrl}")
    suspend fun updateProfileAvatar(
        @Path("avatarUrl") newAvatarUrl: String,
    ): ProfileResponse

    @POST("/api/v1/me/profile/{email}")
    suspend fun updateProfileEmail(
        @Path("email") newEmail: String,
    ): ProfileResponse
}
