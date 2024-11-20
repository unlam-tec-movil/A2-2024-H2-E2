package ar.edu.unlam.mobile.scaffolding.data.remote.dto.request

data class EditProfileRequest(
    val name: String,
    val password: String,
    val avatarUrl: String,
)
