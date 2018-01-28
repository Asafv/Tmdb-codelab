package com.tmdbcodlab.android.api

import com.tmdbcodlab.android.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TmdbApi {

    private var mApi: TmdbService? = null

    val tmdbService: TmdbService?
        get() {
            if (mApi == null) {
                createApi()
            }
            return mApi

        }

    private fun createApi() {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
                .addInterceptor(TmdbClientAuthInterceptor(TmdbService.API_KEY)) // get from BuildConfig.GIPHY_API_KEY
                .addInterceptor(logging)
                .build()

        mApi = Retrofit.Builder()
                .baseUrl(TmdbService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TmdbService::class.java)
    }

    companion object {
        @Volatile private var sSingleton: TmdbApi? = null

        val instance: TmdbApi
            get() {
                if (sSingleton == null) {
                    synchronized(TmdbApi::class.java) {
                        sSingleton = TmdbApi()
                    }
                }
                return sSingleton!!
            }
    }

}
