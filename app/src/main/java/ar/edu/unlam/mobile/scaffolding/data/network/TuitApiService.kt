package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import retrofit2.http.GET
import retrofit2.http.Query

interface TuitApiService {
    @GET("/v1/tuits") // Endpoint actualizado
    suspend fun getTuits(
        @Query("page_id") pageId: Int = 1,
    ): List<Tuit> // Agregado parámetro de página
}
