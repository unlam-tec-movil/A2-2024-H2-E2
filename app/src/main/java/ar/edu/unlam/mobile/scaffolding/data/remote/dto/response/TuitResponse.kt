package ar.edu.unlam.mobile.scaffolding.data.remote.dto.response

data class TuitResponse(
    val id: Int,
    val message: String,
    val author: String,
    val likes: Int,
    val liked: Boolean,
)
