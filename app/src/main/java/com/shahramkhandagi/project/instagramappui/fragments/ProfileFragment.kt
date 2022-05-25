package com.shahramkhandagi.project.instagramappui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahramkhandagi.project.instagramappui.R
import com.shahramkhandagi.project.instagramappui.PostService
import com.shahramkhandagi.project.instagramappui.Posts
import com.shahramkhandagi.project.instagramappui.UserProfilePostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment() {
    lateinit var userProfileAdapter: UserProfilePostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProfile)
        val imageViewProfile = view.findViewById<ImageView>(R.id.imageProfile)

        userProfileAdapter = UserProfilePostAdapter()

        recyclerView.adapter = userProfileAdapter
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)

        Glide.with(requireActivity())
            .load("https://images.unsplash.com/photo-1542909168-82c3e7fdca5c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80")
            .circleCrop()
            .into(imageViewProfile)

        requestGetPosts()
    }
    private fun requestGetPosts() {

        val postService = getRetrofit()!!.create(PostService::class.java)
        val callService = postService.getPost()
        callService.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                val posts = response.body()
                userProfileAdapter.loadedState(posts?.userPostList)
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                Log.d("tagx", "onResponse: ${t.message}")
            }

        })
    }

    fun getRetrofit(): Retrofit? {
        var retrofit: Retrofit? = null
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}
