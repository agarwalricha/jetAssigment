package com.example.jet2travel.dao.remote

import com.example.jet2travel.viewModels.ArticleListResponsePojo
import com.example.jet2travel.viewModels.ArticlePojo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @POST("/jet2/api/v1/blogs")
    fun getArticles(@Query("page") page :Int , @Query("limit") limit :Int, i:Int): Call<List<ArticlePojo?>?>?

    @GET("api/v1/blogs")
    fun getArticles(): Call<List<ArticlePojo?>?>?

    @POST("/jet2/api/v1/blogs")
    fun getArticles(@Query("page") page :Int , @Query("limit") limit :Int): Call<List<ArticlePojo?>?>?
}