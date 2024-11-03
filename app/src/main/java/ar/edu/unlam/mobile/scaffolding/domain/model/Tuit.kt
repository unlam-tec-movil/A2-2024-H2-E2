package ar.edu.unlam.mobile.scaffolding.domain.model

data class Tuit(
    val id: Int,
    val message: String,
    val parentId: Int,
    val author: String,
    val avatarUrl: String,
    val likes: Int,
    val liked: Boolean,
    val date: String,
    val replies: Int = 0
)
