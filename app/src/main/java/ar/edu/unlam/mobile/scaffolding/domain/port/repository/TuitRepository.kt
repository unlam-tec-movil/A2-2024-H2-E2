package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    suspend fun createTuit(content: String): Tuit

    suspend fun getTuits(): Flow<List<Tuit>>

    // Ver si faltan métodos
}
