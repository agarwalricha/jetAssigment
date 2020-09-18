package com.example.assignment.view.home.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.assignment.R
import com.example.jet2travel.viewModels.ArticlePojo

class ArticleAdapter(var articleList: ArrayList<ArticlePojo?>?, val mActivity: Activity) : Adapter<ArticleAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = articleList?.get(position)
        try{
            holder.tvUserName.text = article?.user?.get(0)?.name
            holder.tvDesignation.text = article?.user?.get(0)?.designation
        }catch (e: Exception){ }

        try {
            if(null != article?.media) {
                holder.tvArticleTitle.text = article?.media?.get(0)?.title
                holder.tvArticleURL.text = article?.media?.get(0)?.url
                holder.tvArticleTitle.visibility = View.VISIBLE
                holder.tvArticleURL.visibility = View.VISIBLE
            }
        }catch (e:java.lang.Exception){
            holder.tvArticleTitle.text = ""
            holder.tvArticleURL.text = ""
            holder.tvArticleTitle.visibility = GONE
            holder.tvArticleURL.visibility = GONE
        }

        holder.tvArticleContent.text = article?.content
        holder.tvLikes.text = if (article?.likes!! >=1000)  "${String.format("%.1f",(article?.likes)?.div(1000f))}K Likes" else "${(article?.likes)} Likes"
        holder.tvComments.text = if (article?.comments!! >=1000)  "${String.format("%.1f",(article?.comments)?.div(1000f))}K Comments" else "${(article?.comments)} Comments"

        //holder.tvTime.text = getTime(article?.createdAt)
        holder.tvTime.text = article?.id
        try {
            if (article?.media?.get(0)?.image.isNullOrEmpty()) {
                holder.ivArticleImage.visibility = GONE
            } else {
                Glide.with(mActivity)
                        .load(article?.media?.get(0)?.image)
                        .apply( RequestOptions().override(1000, 50))
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                holder.ivArticleImage.visibility = GONE
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        }).into(holder.ivArticleImage)

                holder.ivArticleImage.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            holder.ivArticleImage.visibility = GONE
        }

        try{
            if (article?.user?.get(0)?.avatar.isNullOrEmpty()) {
                holder.ivAvatar.setImageDrawable( mActivity.getDrawable(R.drawable.ic_user))
            } else {
                Glide.with(mActivity)
                        .load(article?.user?.get(0)?.avatar)
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                holder.ivAvatar.setImageDrawable( mActivity.getDrawable(R.drawable.ic_user))
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        }).into(holder.ivAvatar)
            }
        }catch (e : Exception){}
    }

    private fun getTime(timeReceived: String?): String {
        if (timeReceived.isNullOrBlank()) {
            return ""
        } else {
            return "5 min"
        }
    }

    public fun updateList(articleList :ArrayList<ArticlePojo?>? ){
        this.articleList = articleList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return if (null == articleList) 0 else articleList!!.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var ivAvatar: ImageView
        lateinit var ivArticleImage: ImageView
        lateinit var tvUserName: TextView
        lateinit var tvDesignation: TextView
        lateinit var tvTime: TextView
        lateinit var tvArticleContent: TextView
        lateinit var tvArticleTitle: TextView
        lateinit var tvArticleURL: TextView
        lateinit var tvLikes: TextView
        lateinit var tvComments: TextView

        init {
            ivAvatar = itemView.findViewById(R.id.ivAvatar)
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage)
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvDesignation = itemView.findViewById(R.id.tvDesignation)
            tvTime = itemView.findViewById(R.id.tvTime)
            tvArticleContent = itemView.findViewById(R.id.tvArticleContent)
            tvArticleTitle = itemView.findViewById(R.id.tvArticleTitle)
            tvArticleURL = itemView.findViewById(R.id.tvArticleURL)
            tvLikes = itemView.findViewById(R.id.tvLikes)
            tvComments = itemView.findViewById(R.id.tvComments)
        }

    }
}