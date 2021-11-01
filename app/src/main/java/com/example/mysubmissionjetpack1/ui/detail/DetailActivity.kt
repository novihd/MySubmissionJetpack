package com.example.mysubmissionjetpack1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mysubmissionjetpack1.R
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.databinding.ActivityDetailBinding
import com.example.mysubmissionjetpack1.ui.ViewModelFactory
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.MOVIE
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.TV_SERIES
import com.example.mysubmissionjetpack1.vo.Status

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var binding: ActivityDetailBinding
    private var showId: Int? = 0
    private var categoryShow: String? = null
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.btnFav.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            showId = extras.getInt(EXTRA_SHOW)
            categoryShow = extras.getString(EXTRA_CATEGORY)
            if (showId != null && categoryShow == MOVIE) {
                viewModel.setSelectedShow(showId!!, MOVIE)
                showFavState()
                viewModel.detailMovie.observe(this, {
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                it.data?.let { it1 -> populateMovie(it1) }
                                showLoading(false)
                            }
                            Status.ERROR -> {
                                showLoading(false)
                            }

                        }
                    }

                })
            } else if (showId != null && categoryShow == TV_SERIES) {
                showLoading(true)
                viewModel.setSelectedShow(showId!!, TV_SERIES)
                showFavState()
                viewModel.detailSeries.observe(this, {
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                it.data?.let { it1 -> populateSeries(it1) }
                                showLoading(false)
                            }
                            Status.ERROR -> {
                                showLoading(false)
                            }

                        }
                    }
                })
            }
        }
    }

    private fun populateMovie(showDetailEntity: MovieEntity) {
        binding.apply {
            tvTitleDetail.text = showDetailEntity.title
            tvGenreDetail.text = showDetailEntity.genre
            tvSynopsisDetail.text = showDetailEntity.description
            tvStarDetail.text = showDetailEntity.rating.toString()
            tvDateDetail.text = showDetailEntity.date

            Glide.with(this@DetailActivity)
                    .load("${getString(R.string.link)}${showDetailEntity.poster}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_failed))
                    .into(imgDetail)
        }
    }

    private fun populateSeries(showDetailEntity: TvEntity) {
        binding.apply {
            tvTitleDetail.text = showDetailEntity.title
            tvGenreDetail.text = showDetailEntity.genre
            tvSynopsisDetail.text = showDetailEntity.description
            tvStarDetail.text = showDetailEntity.rating.toString()
            tvDateDetail.text = showDetailEntity.date

            Glide.with(this@DetailActivity)
                    .load("${getString(R.string.link)}${showDetailEntity.poster}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_failed))
                    .into(imgDetail)
        }
    }

    private fun showFavState() {
        if (categoryShow == MOVIE) {
            viewModel.detailMovie.observe(this, {
                when (it.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            showLoading(false)
                            val state = it.data.isFavorite
                            setFavorite(state)
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                    }
                }
            })
        } else if (categoryShow == TV_SERIES) {
            viewModel.detailSeries.observe(this, {
                when (it.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            showLoading(false)
                            val state = it.data.isFavorite
                            setFavorite(state)
                        }
                    }
                    Status.ERROR -> showLoading(false)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
            binding.content.visibility = View.INVISIBLE
            binding.content2.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.content.visibility = View.VISIBLE
            binding.content2.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_fav -> onFavClick()
        }
    }

    private fun setFavorite(state: Boolean) {
        val fav = binding.btnFav
        if (state) {
            fav.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun onFavClick() {
        when (categoryShow) {
            MOVIE -> {
                viewModel.setFavMovies()
            }
            TV_SERIES -> {
                viewModel.setFavSeries()
            }
        }
    }

}