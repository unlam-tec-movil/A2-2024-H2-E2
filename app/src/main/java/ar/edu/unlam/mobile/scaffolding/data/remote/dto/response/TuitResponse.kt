package ar.edu.unlam.mobile.scaffolding.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class TuitResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: String,
    @SerializedName("author") val author: String,
    @SerializedName("likes") val likes: Int,
    @SerializedName("liked") val liked: Boolean,
)
