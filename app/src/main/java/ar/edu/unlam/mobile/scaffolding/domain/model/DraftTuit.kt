package ar.edu.unlam.mobile.scaffolding.domain.model

data class DraftTuit(
    val id: Long? = null,
    val message: String = "",
    val parentId: Int? = null,
    val lastModified: Long = System.currentTimeMillis(),
)
