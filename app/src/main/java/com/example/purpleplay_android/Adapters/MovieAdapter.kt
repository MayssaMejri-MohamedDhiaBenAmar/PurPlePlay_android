package com.example.purpleplay_android.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Model.Movie
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Util.Constant
import com.example.purpleplay_android.Util.ImageLoader
import com.example.purpleplay_android.ViewModel.PlayMovieActivity


class MovieAdapter (var items: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindView(items[position])
    }
    override fun getItemCount(): Int = items.size
    class SearchViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val imageIV: ImageView = itemView.findViewById(R.id.image3)
        private val title: TextView = itemView.findViewById(R.id.titleeee)


        fun bindView(movie: Movie) {

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PlayMovieActivity::class.java)
                intent.putExtra("movie", movie)
                intent.putExtra("class", "MovieAdapter")
                itemView.context.startActivity(intent)
            }

            ImageLoader.setImageFromUrlWithoutProgress(
                imageIV,

                Constant.BASE_URL_IMAGES + movie.imagename
            )
            title.text = movie.title


        }
    }

}