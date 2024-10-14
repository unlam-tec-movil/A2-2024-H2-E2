package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import retrofit2.http.GET

interface TuitApiService {
    @GET("/v1/tuits")
    suspend fun getTuits(): List<Tuit>

}
