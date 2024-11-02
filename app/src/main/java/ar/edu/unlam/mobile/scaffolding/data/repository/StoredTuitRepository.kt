package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.LocalTuitDao
import javax.inject.Inject

class StoredTuitRepository
    @Inject
    constructor(
        private val localTuitDao: LocalTuitDao,
    ) {
    // TODO: Implementar métodos para borradores locales
}
