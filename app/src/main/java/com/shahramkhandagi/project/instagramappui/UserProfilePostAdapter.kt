package com.shahramkhandagi.project.instagramappui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shahramkhandagi.project.instagramappui.UserPost
import io.github.farshidroohi.AdapterRecyclerView

class UserProfilePostAdapter : AdapterRecyclerView<UserPost>(R.layout.item_profile_post, 0, 0, 0) {
    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: UserPost?
    ) {
        val view = viewHolder.itemView
        val imgUserPost = view.findViewById<ImageView>(R.id.imgUserPost)


        Glide
            .with(context)
            .load(element?.postImageUrl)
            .into(imgUserPost)

    }
}