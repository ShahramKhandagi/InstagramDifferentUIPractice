package com.shahramkhandagi.project.instagramappui

import android.content.Context
import android.widget.TextView
import com.bumptech.glide.Glide
import com.qintong.library.InsLoadingView
import io.github.farshidroohi.AdapterRecyclerView

class StoriesAdapter:AdapterRecyclerView<UserPost>(R.layout.item_story,0,0,0) {
    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: UserPost?
    ) {
        val view = viewHolder.itemView
        val storyLoading = view.findViewById<InsLoadingView>(R.id.storyLoading)
        val storyUserText = view.findViewById<TextView>(R.id.textViewUserNameStory)



        storyUserText.text = element?.username
        Glide.with(context)
            .load(element?.userImageProfile)
            .into(storyLoading)
    }

}