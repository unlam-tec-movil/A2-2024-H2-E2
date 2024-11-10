package ar.edu.unlam.mobile.scaffolding.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("name") val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("email") val email: String,
)
