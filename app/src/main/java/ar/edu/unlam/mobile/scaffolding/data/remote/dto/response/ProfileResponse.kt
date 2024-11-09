package ar.edu.unlam.mobile.scaffolding.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("name") val id: String,
    @SerializedName("avatar_url") val message: String,
    @SerializedName("email") val email: String,
)
