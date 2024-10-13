package ar.edu.unlam.mobile.scaffolding.domain.tuit.repository

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    suspend fun crearTuit(content: String): Tuit
    fun getTuits(): Flow<List<Tuit>>
}