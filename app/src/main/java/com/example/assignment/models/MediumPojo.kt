package com.example.jet2travel.viewModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MediumPojo  (id: String? = "", blogId: String? = "", createdAt: String?= "", image: String? = "", title: String? = "", url: String? = "" ) {

    @SerializedName("id")
    @Expose
    public var id: String? = null

    @SerializedName("blogId")
    @Expose
    public var blogId: String? = null

    @SerializedName("createdAt")
    @Expose
    public var createdAt: String? = null

    @SerializedName("image")
    @Expose
    public var image: String? = null

    @SerializedName("title")
    @Expose
    public var title: String? = null

    @SerializedName("url")
    @Expose
    public var url: String? = null

}