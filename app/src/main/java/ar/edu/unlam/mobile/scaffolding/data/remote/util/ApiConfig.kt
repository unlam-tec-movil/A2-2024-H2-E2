package ar.edu.unlam.mobile.scaffolding.data.remote.util

object ApiConfig {
    const val API_BASE_URL = "https://tuiter.fragua.com.ar/"
    const val LOCALHOST_BASE_URL = "http://localhost:8000"
    const val APPLICATION_TOKEN_HEADER = "Application-Token"
    const val USER_TOKEN_HEADER = "Authorization"

    object Endpoints {
        const val LOGIN = "api/v1/login"
        const val REGISTER = "api/v1/users"
        const val PROFILE = "api/v1/me/profile"
        const val FEED = "api/v1/me/feed"
        const val CREATE_TUIT = "api/v1/me/tuits"
    }
}
