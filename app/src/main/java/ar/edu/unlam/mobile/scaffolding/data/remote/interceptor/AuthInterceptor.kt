package ar.edu.unlam.mobile.scaffolding.data.remote.interceptor

import ar.edu.unlam.mobile.scaffolding.BuildConfig
import ar.edu.unlam.mobile.scaffolding.data.remote.util.ApiConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
    @Inject
    constructor(
        private val tokenManager: TokenManager,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val requestBuilder =
                request
                    .newBuilder()
                    .header(
                        ApiConfig.APPLICATION_TOKEN_HEADER,
                        BuildConfig.API_TOKEN,
                    )

            tokenManager.getToken()?.let { token ->
                if (
                    !request.url.encodedPath.contains(ApiConfig.Endpoints.LOGIN) &&
                    !request.url.encodedPath.contains(ApiConfig.Endpoints.REGISTER)
                ) {
                    requestBuilder.header(ApiConfig.USER_TOKEN_HEADER, token)
                }
            }
            return chain.proceed(requestBuilder.build())
        }
    }
