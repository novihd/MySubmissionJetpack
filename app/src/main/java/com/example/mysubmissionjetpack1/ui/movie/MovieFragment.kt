package com.example.mysubmissionjetpack1.ui.movie

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.databinding.FragmentMovieBinding
import com.example.mysubmissionjetpack1.ui.ViewModelFactory
import com.example.mysubmissionjetpack1.ui.detail.DetailActivity
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.MOVIE
import com.example.mysubmissionjetpack1.vo.Status

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            viewModel.getMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> showLoading(false)
                        Status.SUCCESS -> {
                            showMovie(it.data)
                            showLoading(true)
                        }
                        Status.ERROR -> {
                            showLoading(true)
                        }
                    }
                }
            })
        }
    }

    private fun showMovie(list: PagedList<MovieEntity>?) {
        movieAdapter = MovieAdapter(this)
        movieAdapter.submitList(list)
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            2
        } else {
            3
        }
        with(fragmentMovieBinding.rvMovie) {
            layoutManager = GridLayoutManager(context, spanCount)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onItemClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_SHOW, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)
        context?.startActivity(intent)
    }

    private fun showLoading(state:Boolean) {
        fragmentMovieBinding.apply {
            if (state) {
                rvMovie.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            } else {
                rvMovie.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        }
    }

}