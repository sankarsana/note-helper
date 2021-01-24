package com.bhaktaprogram.notehelper.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IconApi {

    @GET("icons/search")
    suspend fun searchIcons(@QueryMap params: Map<String, String>): Response<SearchIconResponse>
}