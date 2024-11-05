package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.remote.api.TuitApi
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteTuitRepository
    @Inject
    constructor(
        private val api: TuitApi,
        private val mapper: TuitMapper
    ) : TuitRepository {

        override fun getFeed(): Flow<List<Tuit>> = flow {
            val response = api.getFeed()
            emit(mapper.toDomainList(response))
        }

        override suspend fun getTuitById(id: Int): Tuit {
            val response = api.getTuitById(id)
            return mapper.toDomain(response)
        }

        override suspend fun createTuit(message: String): Tuit {
            val response = api.createTuit(message)
            return mapper.toDomain(response)
        }

        override suspend fun likeTuit(tuitId: Int) {
            api.likeTuit(tuitId)
        }

        override suspend fun unlikeTuit(tuitId: Int) {
            api.unlikeTuit(tuitId)
        }
    }
