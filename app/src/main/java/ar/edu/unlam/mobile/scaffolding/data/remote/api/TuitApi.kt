package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.request.CreateTuitRequest
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.TuitResponse
import ar.edu.unlam.mobile.scaffolding.data.remote.util.ApiConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TuitApi {
    @GET(ApiConfig.Endpoints.FEED)
    suspend fun getFeed(
        @Query("page") page: Int
    ): List<TuitResponse>

    @GET(ApiConfig.Endpoints.GET_TUIT_BY_ID)
    suspend fun getTuitById(
        @Path("id") id: Int,
    ): TuitResponse

    @POST(ApiConfig.Endpoints.CREATE_TUIT)
    suspend fun createTuit(
        @Body message: CreateTuitRequest,
    ): Response<Unit>

    @POST(ApiConfig.Endpoints.LIKE_TUIT)
    suspend fun likeTuit(
        @Path("id") tuitId: Int,
    )

    @DELETE(ApiConfig.Endpoints.UNLIKE_TUIT)
    suspend fun unlikeTuit(
        @Path("id") tuitId: Int,
    )
}
