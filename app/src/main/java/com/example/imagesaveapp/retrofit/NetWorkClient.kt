package com.example.imagesaveapp.retrofit

import android.os.Build
import androidx.core.os.BuildCompat
import com.google.gson.internal.GsonBuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetWorkClient {
    private const val SEARCH_IMAGE_URL = "https://dapi.kakao.com"


    private fun createOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SEARCH_IMAGE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NetWorkInterface by lazy { retrofit.create(NetWorkInterface::class.java)}
}