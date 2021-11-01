package com.example.mysubmissionjetpack1.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysubmissionjetpack1.databinding.FragmentMovieFavBinding
import com.example.mysubmissionjetpack1.helper.SortUtil.NEWEST
import com.example.mysubmissionjetpack1.helper.SortUtil.OLDEST
import com.example.mysubmissionjetpack1.ui.ViewModelFactory
import com.example.mysubmissionjetpack1.ui.detail.DetailActivity
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.MOVIE

class MovieFavFragment : Fragment(), MovieFavAdapter.OnItemClickCallback {

    private var _fragmentMovieFavBinding: FragmentMovieFavBinding? = null
    private val binding get() = _fragmentMovieFavBinding
    private lateinit var viewModel: MovieFavViewModel
    private lateinit var adapter: MovieFavAdapter
    private var sort = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMovieFavBinding = FragmentMovieFavBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!= null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavViewModel::class.java]

            adapter = MovieFavAdapter(this)
            showLoading(true)

            with(binding?.rvMovieFav) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
            getMovieFavList(sort)
            sortMovie()
        }
    }

    override fun onItemClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_SHOW, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)

        context?.startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.rvMovieFav?.visibility = View.INVISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
            binding?.rvMovieFav?.visibility = View.VISIBLE
        }
    }

    private fun getMovieFavList(sort: String) {
        viewModel.getMovieFav(sort).observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.submitList(it)
                showLoading(false)
            }
        })
    }

    private fun sortMovie() {
        binding?.movNewest?.setOnClickListener{
            getMovieFavList(NEWEST)
        }
        binding?.movOldest?.setOnClickListener{
            getMovieFavList(OLDEST)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieFavBinding = null
    }

}