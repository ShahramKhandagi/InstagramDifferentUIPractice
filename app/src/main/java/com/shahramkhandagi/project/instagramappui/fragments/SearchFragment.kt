package com.shahramkhandagi.project.instagramappui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahramkhandagi.project.instagramappui.R
import com.shahramkhandagi.project.instagramappui.PostAdapter
import com.shahramkhandagi.project.instagramappui.PostService
import com.shahramkhandagi.project.instagramappui.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {
    lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewSearch = view.findViewById<RecyclerView>(R.id.recyclerViewSearch)
        postAdapter = PostAdapter()
        recyclerViewSearch.layoutManager = LinearLayoutManager(requireActivity())
        recyclerViewSearch.adapter = postAdapter


        val edtText = view.findViewById<EditText>(R.id.edtSearch)

        edtText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchKeyWord = textView.text.toString()
                postAdapter.removeAll()
                getSearchKeyWord(searchKeyWord)
                true
            }

            false
        }
    }
    private fun getSearchKeyWord(searchKeyWord: String) {
        val postService = getRetrofit().create(PostService::class.java)
        val callPostList = postService.getSearchResult(searchKeyWord)
        callPostList.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                var posts = response?.body()
                postAdapter.loadedState(posts?.userPostList)
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
