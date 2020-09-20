package com.example.appyhightask.Repo

import com.example.appyhightask.db.ArticleDatabase
import com.example.appyhightask.util.RetrofitInstance

class NewsRepository(
    val db:ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode:String)=
         RetrofitInstance.api.getBreakingNews(countryCode)
}