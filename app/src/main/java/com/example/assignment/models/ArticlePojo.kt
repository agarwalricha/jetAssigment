package com.example.jet2travel.viewModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlePojo (var _id: String?) {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("comments")
    @Expose
    var comments = 0

    @SerializedName("likes")
    @Expose
    var likes = 0

    @SerializedName("media")
    @Expose
    var media: List<MediumPojo>? = null

    @SerializedName("user")
    @Expose
    var user: List<UserPojo>? = null


    constructor(id: String?, createdAt: String?, content: String?, comments: Int, likes: Int, media: List<MediumPojo>?, user: List<UserPojo>? ) : this(id) {
        this.id = id
        this.createdAt = createdAt
        this.content = content
        this.comments = comments
        this.likes = likes
        this.media = media
        this.user = user
    }

}

