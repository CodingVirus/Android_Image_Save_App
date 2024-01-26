package com.example.imagesaveapp.retrofit

import com.example.imagesaveapp.Constants
import com.example.imagesaveapp.data.SearchImage
import com.example.imagesaveapp.data.SearchImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("/v2/search/image")
    suspend fun getImage(
        @Header("Authorization") apiKey: String = Constants.AUTH_HEADER,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): SearchImageResponse
}