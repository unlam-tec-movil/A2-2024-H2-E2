package ar.edu.unlam.mobile.scaffolding.data.local.mapper

import ar.edu.unlam.mobile.scaffolding.data.local.entity.DraftTuitEntity
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DraftTuitMapper @Inject constructor() {
    fun mapToDomain(draftTuitEntity: DraftTuitEntity): DraftTuit {
        return DraftTuit(
            message = draftTuitEntity.message,
            lastModified = draftTuitEntity.lastModified
        )
    }

    fun mapToEntity(draftTuit: DraftTuit): DraftTuitEntity {
        return DraftTuitEntity(
            message = draftTuit.message,
            lastModified = draftTuit.lastModified
        )
    }

    fun mapToDomainList(draftTuitEntityFlow: Flow<List<DraftTuitEntity>>): Flow<List<DraftTuit>> {
        return draftTuitEntityFlow.map { entities ->
            entities.map { mapToDomain(it) }
        }
    }
}
