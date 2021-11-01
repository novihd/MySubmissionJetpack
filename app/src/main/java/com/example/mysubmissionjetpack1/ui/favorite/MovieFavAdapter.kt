package com.example.mysubmissionjetpack1.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mysubmissionjetpack1.R
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.databinding.ItemFavMovieBinding

class MovieFavAdapter(private val callback: OnItemClickCallback) :
    PagedListAdapter<MovieEntity, MovieFavAdapter.MovieFavViewHolder>(DIFF_CALLBACK) {

        companion object {
            private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.showId == newItem.showId
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem == newItem
                }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavAdapter.MovieFavViewHolder {
        val itemFavMovieBinding = ItemFavMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavViewHolder(itemFavMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieFavAdapter.MovieFavViewHolder, position: Int) {
        return holder.bind(getItem(position) as MovieEntity)
    }

    inner class MovieFavViewHolder(private val binding: ItemFavMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvMovieTitleFav.text = movie.title
                tvMovieDateFav.text = movie.date
                tvMovieRatingFav.text = movie.rating.toString()
                itemView.setOnClickListener { callback.onItemClicked(movie.showId) }
                Glide.with(itemView.context)
                        .load("${itemView.context.getString(R.string.link)}${movie.poster}")
                        .transform(RoundedCorners(20))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(imgMovieFav)
            }
        }
    }


    interface OnItemClickCallback{
        fun onItemClicked(id: Int)
    }
}