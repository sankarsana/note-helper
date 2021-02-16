package com.bhaktaprogram.notehelper.repository

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IconApiProvider {

    fun create(): IconApi {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val clientBuilder = OkHttpClient.Builder().apply {
            addInterceptor { addHeaders(it) }
            addInterceptor(logger)
        }

        return Retrofit.Builder()
            .baseUrl("https://api.iconfinder.com/v4/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(clientBuilder.build())
            .build()
            .create(IconApi::class.java)
    }

    private fun addHeaders(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", AUTH_TOKEN)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val AUTH_TOKEN =
            "Bearer X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
    }
}