package com.example.purpleplay_android.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Models.Music
import com.example.purpleplay_android.Models.Post
import com.example.purpleplay_android.R
import com.example.purpleplay_android.ViewModel.PlaylistActivity

class MusicAdapter(var items: MutableList<Music>) :
    RecyclerView.Adapter<MusicAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.music_view, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindView(items[position])
    }
    override fun getItemCount(): Int = items.size
    class SearchViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val imageIV: ImageView = itemView.findViewById(R.id.imageMV)
        private val descriptionTV: TextView = itemView.findViewById(R.id.songNameMV)
        private val descriptionAL: TextView = itemView.findViewById(R.id.songAlbumMV)

        private val duration: TextView = itemView.findViewById(R.id.songDuration)

        fun bindView(music: Music) {

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PlaylistActivity::class.java)
                intent.putExtra("music", music)
                intent.putExtra("class", "MusicAdapter")

                itemView.context.startActivity(intent)
            }

           /* ImageLoader.setImageFromUrl(
                imageIV,
                duration ,
                Constant.BASE_URL_MUSIC +
            )*/
            music.filename
            descriptionTV.text = music.title
            descriptionAL.text = music.artist


        }
    }

}