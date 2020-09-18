package com.example.assignment.view

import android.arch.lifecycle.MutableLiveData
import com.example.jet2travel.viewModels.ArticlePojo

class ArticleSession {

    var articleList: MutableLiveData<Array<ArticlePojo?>?> = MutableLiveData()


    companion object{
        private var INSTANCE: ArticleSession? = null
        @Synchronized
        fun getInstance(): ArticleSession? {
            if (null == INSTANCE) {
                INSTANCE = ArticleSession()
            }
            return INSTANCE
        }

        fun clear() {
            INSTANCE = null
        }
    }


    fun setArticleList(_articleList: Array<ArticlePojo?>?) {
        this.articleList.apply { value = _articleList}
    }

}