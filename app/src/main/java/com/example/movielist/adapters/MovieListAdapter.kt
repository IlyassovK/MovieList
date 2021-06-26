package com.example.movielist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.data.model.Movie
import com.example.movielist.data.model.Result
import com.example.movielist.databinding.MovieItemBinding

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    class MovieListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    val diffCallback = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.display_title == newItem.display_title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun getChangePayload(oldItem: Result, newItem: Result): Any? {
            return super.getChangePayload(oldItem, newItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList[position]
        val binding = MovieItemBinding.bind(holder.itemView)

        holder.itemView.apply {
            binding.tvMovieTitle.text = movie.display_title
            binding.tvMovieDescription.text = movie.summary_short

            Glide.with(context).load(movie.multimedia.src).into(binding.ivMovieImage)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    val differ = AsyncListDiffer(this, diffCallback)

    var movieList: List<Result>
        get() = differ.currentList
        set(value) = differ.submitList(value)

}