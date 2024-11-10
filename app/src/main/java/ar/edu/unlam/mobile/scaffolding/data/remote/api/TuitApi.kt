package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.TuitResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TuitApi {
    @GET("/api/v1/me/feed")
    suspend fun getFeed(): List<TuitResponse>

    @GET("/api/v1/me/tuits/{id}")
    suspend fun getTuitById(
        @Path("id") id: Int,
    ): TuitResponse

    @POST("/api/v1/me/tuits")
    suspend fun createTuit(
        @Body message: String,
    ): Response<Unit>

    @POST("/api/v1/me/tuits/{id}/likes")
    suspend fun likeTuit(
        @Path("id") tuitId: Int,
    )

    @DELETE("/api/v1/me/tuits/{id}/likes")
    suspend fun unlikeTuit(
        @Path("id") tuitId: Int,
    )
}
