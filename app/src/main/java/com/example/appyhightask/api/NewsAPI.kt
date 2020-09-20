package com.example.appyhightask.api

import com.example.appyhightask.API_KEY
import com.example.appyhightask.models.newsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String="IN",
        @Query("apikey")
        apikey:String=API_KEY
    ):Response<newsResponse>
}