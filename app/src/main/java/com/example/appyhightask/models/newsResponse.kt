package com.example.appyhightask.models

import com.example.appyhightask.models.Article

data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)