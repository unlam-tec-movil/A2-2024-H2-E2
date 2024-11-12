package ar.edu.unlam.mobile.scaffolding.domain.model

data class DraftTuit(
    val message: String,
    val lastModified: Long = System.currentTimeMillis(),
    val userId: String = "",
)
