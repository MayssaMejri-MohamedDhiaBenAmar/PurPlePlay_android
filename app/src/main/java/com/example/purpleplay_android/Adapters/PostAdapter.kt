package com.example.purpleplay_android.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Models.Post
import com.example.purpleplay_android.R
import com.example.purpleplay_android.ViewModel.PlaylistActivity

class PostAdapter(var items: MutableList<Post>) :
    RecyclerView.Adapter<PostAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.posts_view, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindView(items[position])
    }
    override fun getItemCount(): Int = items.size
    class SearchViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val comment: TextView = itemView.findViewById(R.id.comment)


        fun bindView(post: Post) {

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PlaylistActivity::class.java)
                intent.putExtra("post", post)
                intent.putExtra("class", "PostAdapter")

                itemView.context.startActivity(intent)
            }


            comment.text = post.description


        }
    }

}