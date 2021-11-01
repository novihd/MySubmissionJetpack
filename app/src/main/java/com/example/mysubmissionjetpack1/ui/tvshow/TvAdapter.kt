package com.example.mysubmissionjetpack1.ui.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mysubmissionjetpack1.R
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.databinding.ItemTvBinding

class TvAdapter(private val callback: OnItemClickCallback) : PagedListAdapter<TvEntity, TvAdapter.ShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
               return oldItem.showId == newItem.showId
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val itemTvBinding = ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(itemTvBinding)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = getItem(position)
        if (show != null) {
            holder.bind(show)
        }
    }

    inner class ShowViewHolder(private val binding: ItemTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(show: TvEntity) {
            with(binding) {
                tvTitleTv.text = show.title
                tvDateTv.text = show.date
                tvStarTv.text = show.rating.toString()
                itemView.setOnClickListener{
                    callback.onItemClicked(show.showId)
                }
                Glide.with(itemView.context)
                    .load("${itemView.context.getString(R.string.link)}${show.poster}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_failed))
                    .into(imgTv)
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(id: Int)
    }
}