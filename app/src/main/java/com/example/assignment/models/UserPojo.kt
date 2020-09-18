package com.example.jet2travel.viewModels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class UserPojo {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("blogId")
    @Expose
    var blogId: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

    @SerializedName("lastname")
    @Expose
    var lastname: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("designation")
    @Expose
    var designation: String? = null

    @SerializedName("about")
    @Expose
    var about: String? = null

    constructor() {}

    constructor(id: String?, blogId: String?, createdAt: String?, name: String?, avatar: String?, lastname: String?, city: String?, designation: String?, about: String?) : super() {
        this.id = id
        this.blogId = blogId
        this.createdAt = createdAt
        this.name = name
        this.avatar = avatar
        this.lastname = lastname
        this.city = city
        this.designation = designation
        this.about = about
    }

}