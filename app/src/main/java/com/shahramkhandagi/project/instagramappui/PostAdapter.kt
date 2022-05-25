package com.shahramkhandagi.project.instagramappui

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.farshidroohi.AdapterRecyclerView

class PostAdapter : AdapterRecyclerView<UserPost>(R.layout.item_post, 0, 0, 0) {
    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: UserPost?
    ) {
        val view = viewHolder.itemView
        val textViewUserName = view.findViewById<TextView>(R.id.textViewUserName)
        val imageViewProfile = view.findViewById<ImageView>(R.id.imageViewProfile)
        val mainPost = view.findViewById<ImageView>(R.id.mainPost)
        val txtLike = view.findViewById<TextView>(R.id.textLikes)


        textViewUserName.text = element?.username
        txtLike.text = element?.likes.toString() + "likes"

        Glide.with(context)
            .load(element?.userImageProfile)
            .into(imageViewProfile)

        Glide.with(context)
            .load(element?.postImageUrl)
            .into(mainPost)

    }
}