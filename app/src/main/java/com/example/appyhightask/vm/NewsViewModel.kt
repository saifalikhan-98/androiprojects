package com.example.appyhightask.vm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appyhightask.Repo.NewsRepository
import com.example.appyhightask.models.newsResponse
import com.example.appyhightask.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
):ViewModel() {

    val breakingnews:MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    init{
        getBreakingNews("in")


    }

        fun getBreakingNews(countryCode: String) = viewModelScope.launch {
            breakingnews.postValue(Resource.Loading())

            val response = newsRepository.getBreakingNews(countryCode)
            breakingnews.postValue(HandlingBreakingNEwsResponse(response))


        }

    private fun HandlingBreakingNEwsResponse(response: Response<newsResponse>):Resource<newsResponse>{
        if(response.isSuccessful){
                response.body()?.let {resultResponse ->
                    return Resource.Success(resultResponse)
                }
        }
        return Resource.Error(response.message())

    }
}