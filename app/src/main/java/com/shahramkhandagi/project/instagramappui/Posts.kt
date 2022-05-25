package com.shahramkhandagi.project.instagramappui

import com.google.gson.annotations.SerializedName

class Posts {

    @SerializedName("hits")
    var userPostList: ArrayList<UserPost>? = null

}