package ar.edu.unlam.mobile.scaffolding.data.local.mapper

import ar.edu.unlam.mobile.scaffolding.data.local.entity.LocalProfileEntity
import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import javax.inject.Inject

class LocalProfileMapper
@Inject
constructor() {

    fun mapToDomain(entity: LocalProfileEntity): Profile {
        return Profile(
            name = entity.name,
            avatarUrl = entity.avatarUrl,
            email = entity.email
        )
    }

    fun mapToEntity(domain: Profile): LocalProfileEntity {
        return LocalProfileEntity(
            name = domain.name,
            avatarUrl = domain.avatarUrl,
            email = domain.email
        )
    }
}
