package ar.edu.unlam.mobile.scaffolding.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class LikeTuitRequest(
    @SerializedName("tuitID") val tuitID: Int
)