package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed

import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshFeed
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        operator fun invoke(): Flow<List<Tuit>> =
            flow {
                tuitRepository.getFeed(1).collect { feed ->
                    emit(feed)
                }
            }
    }
