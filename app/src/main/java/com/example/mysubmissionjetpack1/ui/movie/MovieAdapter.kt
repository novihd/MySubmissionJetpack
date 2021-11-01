package com.example.mysubmissionjetpack1.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mysubmissionjetpack1.R
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.databinding.ItemMovieBinding


class MovieAdapter(private val callback: OnItemClickCallback) : PagedListAdapter<MovieEntity, MovieAdapter.ShowViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = getItem(position)
        if (show != null) {
            holder.bind(show)
        }
    }

    inner class ShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(show: MovieEntity) {
            with(binding) {
                tvTitleMovie.text = show.title
                tvDateMovie.text = show.date
                tvStarMovie.text = show.rating.toString()
                itemView.setOnClickListener{
                    callback.onItemClicked(show.showId)
                }
                Glide.with(itemView.context)
                    .load("${itemView.context.getString(R.string.link)}${show.poster}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_failed))
                    .into(imgMovie)
            }
        }

    }

    interface OnItemClickCallback{
        fun onItemClicked(id: Int)
    }
}