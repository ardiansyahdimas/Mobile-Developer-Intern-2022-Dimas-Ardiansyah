package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.network

import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.api.response.ListUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getListUser(@Query("page") pageNumber: Int, @Query("per_page") pageSize: Int): Call<ListUserResponse>
}