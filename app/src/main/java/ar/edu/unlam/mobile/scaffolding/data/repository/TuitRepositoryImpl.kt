package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.network.TuitApiService
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TuitRepositoryImpl(
    private val tuitApiService: TuitApiService,
) : TuitRepository {
    override fun getTuits(): Flow<List<Tuit>> =
        flow {
            val tuits = tuitApiService.getTuits() // Llama a la API
            emit(tuits) // Emite los tuits obtenidos
        }

    override fun createTuit(tuit: Tuit) {
        // Lógica para crear tuits, TODO porque todavia no lo desarrollamos
    }
}
