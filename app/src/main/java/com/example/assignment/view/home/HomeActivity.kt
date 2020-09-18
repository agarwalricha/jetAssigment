package com.example.jet2travel.view.home

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.view.home.adapter.ArticleAdapter
import com.example.assignment.view.utils.ErrorBaseActivity
import com.example.assignment.viewModels.HomeViewModel
import com.example.assignment.viewModels.ViewModelFactory


class HomeActivity : ErrorBaseActivity() {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var articleAdapter: ArticleAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var rvArticles: RecyclerView? = null

    var mContext: Context? = null
    lateinit var homeViewModel: HomeViewModel
    var page = 1
    var limit = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        setViewListener()
        init()
        initialisePregressDialog()
        setUpRecyclerAdapter()
        setViewModelObserver()
        fetchArticleListFromServer()
    }

    private fun initView() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        rvArticles = findViewById(R.id.rvArticles)
    }

    private fun setViewListener() {
        swipeRefreshLayout.setOnRefreshListener {
            //fetchArticleListFromAsset()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun init() {
        mContext = this
        homeViewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(HomeViewModel::class.java)
    }

    private fun setUpRecyclerAdapter() {
        val manager = LinearLayoutManager(mContext)
        articleAdapter = ArticleAdapter(null, this)
        rvArticles!!.setAdapter(articleAdapter)
        rvArticles!!.setLayoutManager(manager)
        rvArticles!!.addItemDecoration(DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL)
                .apply { setDrawable(ContextCompat.getDrawable(mContext!!, R.drawable.item_separator)!!) }
        )
        /*val decorator = DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(mContext!!, R.drawable.item_separator)!!)
        rvArticles!!.addItemDecoration(decorator)*/
    }

    private fun setViewModelObserver() {
        homeViewModel?.articleList?.observe(this, Observer {
            hideProgressDialog()
            if (null == it) {
                showErrorDialog(this, "", getString(R.string.no_article))
            } else {
                articleAdapter.updateList(ArrayList(it))
            }
        })
    }

    private fun fetchArticleListFromServer() {
        showProgressDialog()
        homeViewModel.getArticleListFromServer(page, limit)
    }

    private fun initialisePregressDialog() {
        progressDialog = ProgressDialog(this)
        if (progressDialog == null) {
            progressDialog.setCancelable(false)
        }
    }

    protected fun showProgressDialog() {
        if (progressDialog == null) {
            initialisePregressDialog()
        }
        if(!progressDialog.isShowing)
            progressDialog.show()
    }
    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}
