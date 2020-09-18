package com.example.assignment.view.home.adapter.paging

import android.app.Activity
import android.arch.paging.PagedListAdapter
import android.content.ClipData.Item
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.assignment.R
import com.example.jet2travel.viewModels.ArticlePojo


class HomePagedAdapter constructor(var articleList: ArrayList<ArticlePojo?>?, val mActivity: Activity) : PagedListAdapter<ArrayList<ArticlePojo?>?, HomePagedAdapter.ArticleViewHolder?>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.assignment.R.layout.row_article, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
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
            holder.tvArticleTitle.visibility = View.GONE
            holder.tvArticleURL.visibility = View.GONE
        }

        holder.tvArticleContent.text = article?.content
        holder.tvLikes.text = if (article?.likes!! >=1000)  "${String.format("%.1f",(article?.likes)?.div(1000f))}K Likes" else "${(article?.likes)} Likes"
        holder.tvComments.text = if (article?.comments!! >=1000)  "${String.format("%.1f",(article?.comments)?.div(1000f))}K Comments" else "${(article?.comments)} Comments"

        //holder.tvTime.text = getTime(article?.createdAt)
        holder.tvTime.text = article?.id
        try {
            if (article?.media?.get(0)?.image.isNullOrEmpty()) {
                holder.ivArticleImage.visibility = View.GONE
            } else {
                Glide.with(mActivity)
                        .load(article?.media?.get(0)?.image)
                        .apply( RequestOptions().override(1000, 50))
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                holder.ivArticleImage.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        }).into(holder.ivArticleImage)

                holder.ivArticleImage.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            holder.ivArticleImage.visibility = View.GONE
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

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
            ivAvatar = itemView.findViewById(com.example.assignment.R.id.ivAvatar)
            ivArticleImage = itemView.findViewById(com.example.assignment.R.id.ivArticleImage)
            tvUserName = itemView.findViewById(com.example.assignment.R.id.tvUserName)
            tvDesignation = itemView.findViewById(com.example.assignment.R.id.tvDesignation)
            tvTime = itemView.findViewById(com.example.assignment.R.id.tvTime)
            tvArticleContent = itemView.findViewById(com.example.assignment.R.id.tvArticleContent)
            tvArticleTitle = itemView.findViewById(com.example.assignment.R.id.tvArticleTitle)
            tvArticleURL = itemView.findViewById(com.example.assignment.R.id.tvArticleURL)
            tvLikes = itemView.findViewById(com.example.assignment.R.id.tvLikes)
            tvComments = itemView.findViewById(com.example.assignment.R.id.tvComments)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ArticlePojo> = object : DiffUtil.ItemCallback<ArticlePojo>() {
            override fun areItemsTheSame(oldArticle: ArticlePojo, newArticle: ArticlePojo): Boolean {
                return oldArticle.id === newArticle.id
            }

            override fun areContentsTheSame(oldArticle: ArticlePojo, newArticle: ArticlePojo): Boolean {
                return oldArticle == newArticle
            }
        }
    }

}