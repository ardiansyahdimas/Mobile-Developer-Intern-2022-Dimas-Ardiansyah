package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.network

import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private val interceptor = if(BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    private const val BASE_URL = "https://reqres.in/api/"
    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(600, TimeUnit.SECONDS)
        .connectTimeout(600, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: ApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiService::class.java)
    }
}


