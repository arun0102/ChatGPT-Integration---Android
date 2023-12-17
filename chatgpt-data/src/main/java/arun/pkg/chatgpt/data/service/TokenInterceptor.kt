package arun.pkg.chatgpt.data.service

import arun.pkg.chatgpt.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request()
            .newBuilder()
            .header("Authorization", "Bearer " + BuildConfig.CHATGPT_SECRET_KEY)
            .build()
    )
}