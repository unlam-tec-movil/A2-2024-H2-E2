package ar.edu.unlam.mobile.scaffolding.data.remote.mapper

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.TuitResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import javax.inject.Inject

class TuitMapper
    @Inject
    constructor() {
        private fun toDomain(response: TuitResponse): Tuit {
            return Tuit(
                id = response.id,
                message = response.message,
                author = response.author,
                likes = response.likes,
                liked = response.liked,
                parentId = 0,
                avatarUrl = "https://ui-avatars.com/api/?name=${response.author.replace(" ", "+")}",
                date = System.currentTimeMillis().toString(),
                replies = 0,
            )
        }

    fun toDomainList(responses: List<TuitResponse>): List<Tuit> {
        return responses.map { toDomain(it) }
    }
}
