package ar.edu.unlam.mobile.scaffolding.data.remote.mapper

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.TuitResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import javax.inject.Inject

class TuitMapper
    @Inject
    constructor() {
        fun toDomain(response: TuitResponse): Tuit {
            return Tuit(
                id = response.id,
                message = response.message,
                author = response.author,
                likes = response.likes,
                liked = response.liked,
                parentId = response.parentId,
                avatarUrl = response.avatarUrl,
                date = response.date,
                replies = 0,
            )
        }

        fun toDomainList(responses: List<TuitResponse>): List<Tuit> {
            return responses.map { toDomain(it) }
        }
    }
