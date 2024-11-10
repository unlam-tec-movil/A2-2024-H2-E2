package ar.edu.unlam.mobile.scaffolding.data.remote.interceptor

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager
    @Inject
    constructor(
        private val sharedPreferences: SharedPreferences,
    ) {
        companion object {
            private const val KEY_TOKEN = "user_token"
        }

        fun saveToken(token: String) {
            sharedPreferences
                .edit()
                .putString(KEY_TOKEN, token)
                .apply()
        }

        fun getToken(): String? {
            return sharedPreferences
                .getString(
                    KEY_TOKEN,
                    null,
                )
        }

        fun clearToken() {
            sharedPreferences
                .edit()
                .remove(KEY_TOKEN)
                .apply()
        }
    }
