package com.example.jet2travel.dao.remote


import com.example.assignment.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    private var retrofit : Retrofit? = null
    private fun getRetrofitInstance(): Retrofit? {
        if (null == retrofit) {
            val API_BASE_URL = BuildConfig.baseUrl


            val gson = GsonBuilder().setLenient().create()
            //val gson = GsonBuilder().create()
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(httpClientBuilder)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
        return retrofit;
    }

    @Synchronized
     fun getRetrofitApiClient(): ApiClient {
        var retrofitInstance = getRetrofitInstance()
        return  retrofitInstance!!.create(ApiClient::class.java)
    }

}