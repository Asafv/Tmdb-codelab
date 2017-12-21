package com.tmdbcodlab.android.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by AsafV on 21/12/2017.
 */
internal class TmdbClientAuthInterceptor(private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}