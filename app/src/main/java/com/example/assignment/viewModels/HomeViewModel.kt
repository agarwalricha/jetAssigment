package com.example.assignment.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.NonNull
import android.util.Log
import com.example.assignment.repository.Repository
import com.example.jet2travel.viewModels.ArticleListResponsePojo
import com.example.jet2travel.viewModels.ArticlePojo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel (@NonNull application : Application) : AndroidViewModel(application) {
    private val repository: Repository by lazy { Repository.getRepositoryInstance() }
    var articleList: MutableLiveData<List<ArticlePojo?>?> = MutableLiveData()

    public fun getArticleListFromServer(page : Int, limit: Int) {
        /*var call = repository.getArticleList(page, limit)
        try{
            call?.enqueue(object : Callback<List<ArticlePojo?>?> {
                override fun onResponse(call: Call<List<ArticlePojo?>?>, response: Response<List<ArticlePojo?>?>) {
                    if (response.code() == 200) {
                        val weatherResponse = response.body()!!
                    }
                    Log.d("richa","onResponse")
                }

                override fun onFailure(call: Call<List<ArticlePojo?>?>, t: Throwable) {
                    var a : Int //weatherData!!.text = t.message
                    Log.d("richa","onFailure")
                }
            })
        }catch (e:Exception){
            e.printStackTrace()
        }*/
        var call = repository.getArticleList()
        try{
            call?.enqueue(object : Callback<List<ArticlePojo?>?>{
                override fun onFailure(call: Call<List<ArticlePojo?>?>?, t: Throwable) {
                    Log.d("richa","onFailure")

                }

                override fun onResponse(call: Call<List<ArticlePojo?>?>?, response: Response<List<ArticlePojo?>?>?) {
                    Log.d("richa", "onResponse")
                    articleList.value = response?.body()
                }

            })
        }catch (e:Exception) {
            e.printStackTrace()
        }

    }
}