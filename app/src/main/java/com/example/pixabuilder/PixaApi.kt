package com.example.pixabuilder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("q") searchName: String,
        @Query("key") key: String = "38012561-f0e5b662b2fdb087b41538b87",
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int
    ) : Call<PixaModel>
}