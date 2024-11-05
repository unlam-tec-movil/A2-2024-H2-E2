package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    fun getFeed(): Flow<List<Tuit>>

    suspend fun getTuitById(id: Int): Tuit

    suspend fun createTuit(message: String): Tuit

    suspend fun likeTuit(tuitId: Int)

    suspend fun unlikeTuit(tuitId: Int)
}
