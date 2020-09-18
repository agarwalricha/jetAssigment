package com.example.assignment.repository

import android.app.Activity
import com.example.assignment.view.ArticleSession
import com.example.jet2travel.dao.remote.RetrofitClient
import com.example.jet2travel.viewModels.ArticlePojo
import com.google.gson.Gson
import retrofit2.Call
import java.io.IOException
import java.io.InputStream

class Repository {

    var mActivity : Activity? = null
    var articleSession : ArticleSession? = null


    private val retrofitApiClient by lazy { RetrofitClient().getRetrofitApiClient() }

    constructor(){}
    constructor(activity : Activity){
        mActivity = activity;
        articleSession = ArticleSession.getInstance()
    }

    companion object{
        public fun getRepositoryInstance() : Repository = Repository()
    }

    public fun getArticleList( page:Int, limit: Int ) : Call<List<ArticlePojo?>?>?{
        val call = retrofitApiClient.getArticles(page,limit)
        return call
    }/*public fun getArticleList( page:Int, limit: Int ) : Call<List<ArticlePojo?>?>?{
        val call = retrofitApiClient.getArticles(page,limit)
        return call
    }*/
    public fun getArticleList() : Call<List<ArticlePojo?>?>?{
        val call = retrofitApiClient.getArticles()
        return call
    }
/*
public fun getArticleList() : Call<ResponseBody>{
        val call = retrofitApiClient.getArticles()
        return call
    }
*/



    fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val inputStream : InputStream = mActivity!!.getAssets().open("response.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    public fun getArticleListFromAsset(){
        val articleList: Array<ArticlePojo?>? = Gson().fromJson(loadJSONFromAsset().toString(), Array<ArticlePojo?>::class.java )
        articleSession!!.setArticleList(articleList)

    }
}