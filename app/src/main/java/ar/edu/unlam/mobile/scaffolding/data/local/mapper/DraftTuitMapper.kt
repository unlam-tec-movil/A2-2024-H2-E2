package ar.edu.unlam.mobile.scaffolding.data.local.mapper

import ar.edu.unlam.mobile.scaffolding.data.local.entity.DraftTuitEntity
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import javax.inject.Inject

class DraftTuitMapper
    @Inject
    constructor() {
        fun mapToDomain(draftTuitEntity: DraftTuitEntity): DraftTuit {
            return DraftTuit(
                id = draftTuitEntity.id,
                message = draftTuitEntity.message,
                lastModified = draftTuitEntity.lastModified,
                userEmail = draftTuitEntity.userEmail,
            )
        }

        fun mapToEntity(draftTuit: DraftTuit): DraftTuitEntity {
            return DraftTuitEntity(
                id = draftTuit.id,
                message = draftTuit.message,
                lastModified = draftTuit.lastModified,
                userEmail = draftTuit.userEmail,
            )
        }
    }
