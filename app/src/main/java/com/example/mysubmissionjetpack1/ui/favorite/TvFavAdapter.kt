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
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.databinding.ItemFavTvBinding

class TvFavAdapter(private val callback: OnItemClickCallback) :
        PagedListAdapter<TvEntity, TvFavAdapter.TvFavViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvFavAdapter.TvFavViewHolder {
        val itemFavTvBinding = ItemFavTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvFavViewHolder(itemFavTvBinding)
    }

    override fun onBindViewHolder(holder: TvFavAdapter.TvFavViewHolder, position: Int) {
        return holder.bind(getItem(position) as TvEntity)
    }

    inner class TvFavViewHolder(private val binding: ItemFavTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: TvEntity) {
            with(binding) {
                tvTvTitleFav.text = series.title
                tvTvDateFav.text = series.date
                tvTvRatingFav.text = series.rating.toString()
                itemView.setOnClickListener { callback.onItemClicked(series.showId) }
                Glide.with(itemView.context)
                        .load("${itemView.context.getString(R.string.link)}${series.poster}")
                        .transform(RoundedCorners(20))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(imgTvFav)
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(id: Int)
    }
}