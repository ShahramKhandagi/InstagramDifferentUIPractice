package com.shahramkhandagi.project.instagramappui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahramkhandagi.project.instagramappui.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    lateinit var postAdapter: PostAdapter
    lateinit var storiesAdapter: StoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Post Adapter ///////////////////////
        val recyclerViewMain = view.findViewById<RecyclerView>(R.id.recyclerViewMain)
        postAdapter = PostAdapter()
        recyclerViewMain.layoutManager = LinearLayoutManager(requireActivity())
        recyclerViewMain.adapter = postAdapter
        // Post Adapter ///////////////////////

        // Story Adapter ///////////////////////
        val recyclerViewStory = view.findViewById<RecyclerView>(R.id.recyclerViewStory)
        storiesAdapter = StoriesAdapter()
        recyclerViewStory.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.HORIZONTAL, false)
        recyclerViewStory.adapter = storiesAdapter
        // Story Adapter ///////////////////////


        requestGetPosts()
    }

    private fun requestGetPosts() {
        val postService = getRetrofit().create(PostService::class.java)
        val callPostList = postService.getPost()

        callPostList.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                var posts = response?.body()
                postAdapter.loadedState(posts?.userPostList)
                storiesAdapter.loadedState(posts?.userPostList)
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                Log.d("tagx", "onFailure: ${t.message}")
            }

        })
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
