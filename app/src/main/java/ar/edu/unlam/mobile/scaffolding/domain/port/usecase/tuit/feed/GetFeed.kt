package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed

import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeed
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        operator fun invoke(page: Int): Flow<List<Tuit>> = tuitRepository.getFeed(page)
    }
