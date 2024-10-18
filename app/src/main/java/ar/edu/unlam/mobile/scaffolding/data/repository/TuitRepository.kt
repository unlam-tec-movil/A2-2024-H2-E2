package ar.edu.unlam.mobile.scaffolding.data.repository
import ar.edu.unlam.mobile.scaffolding.data.network.TuitApiService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TuitRepository : TuitApiService {
    private val apiService: TuitApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TuitApiService::class.java)
    }

    override suspend fun getTuits(): List<Tuit> {
        return apiService.getTuits()
    }
}
