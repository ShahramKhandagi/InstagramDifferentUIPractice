package com.shahramkhandagi.project.instagramappui

import com.shahramkhandagi.project.instagramappui.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("https://pixabay.com/api/?key=25690734-ddbeec46bd4b803f0ab109acc&q=yellow+flowers&pretty=true")
    fun getPost():Call<Posts>


    @GET("https://pixabay.com/api/?key=25690734-ddbeec46bd4b803f0ab109acc&pretty=true")
    fun getSearchResult(@Query("q") SearchKeyWord: String):Call<Posts>
}