package com.shahramkhandagi.project.instagramappui

import com.google.gson.annotations.SerializedName

class UserPost {


    @SerializedName("webformatURL")
    var postImageUrl: String? = null

    @SerializedName("userImageURL")
    var userImageProfile: String? = null

    @SerializedName("user")
    var username: String? = null


    var tags: String? = null
    var views: Int? = null
    var likes: Int? = null
    var comments: Int? = null








}