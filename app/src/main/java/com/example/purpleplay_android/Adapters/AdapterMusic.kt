package com.example.purpleplay_android.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Models.Music
import com.example.purpleplay_android.R

class AdapterMusic(private val dataList: List<Music>) :
    RecyclerView.Adapter<AdapterMusic.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.songAlbumMV)
        val bodyTextView: TextView = itemView.findViewById(R.id.songname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.title
        holder.bodyTextView.text = data.filename
    }

    override fun getItemCount(): Int = dataList.size
}