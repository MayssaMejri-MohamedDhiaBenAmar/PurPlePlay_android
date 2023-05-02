package com.example.purpleplay_android.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Model.Movie
import com.example.purpleplay_android.Model.Music
import com.example.purpleplay_android.R

class AdapterMovie(private val dataList: List<Movie>) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleeee)
        val image: ImageView = itemView.findViewById(R.id.image3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.title
      //  holder.image.im = data.filename
    }

    override fun getItemCount(): Int = dataList.size
}