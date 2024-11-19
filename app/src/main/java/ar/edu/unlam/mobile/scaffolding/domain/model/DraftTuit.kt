package ar.edu.unlam.mobile.scaffolding.domain.model

data class DraftTuit(
    val id: Int = 0,
    val message: String,
    val lastModified: Long = System.currentTimeMillis(),
    val userEmail: String,
)
