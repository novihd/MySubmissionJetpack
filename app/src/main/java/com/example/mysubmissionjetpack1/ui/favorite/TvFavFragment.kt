package com.example.mysubmissionjetpack1.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysubmissionjetpack1.databinding.FragmentTvFavBinding
import com.example.mysubmissionjetpack1.helper.SortUtil
import com.example.mysubmissionjetpack1.ui.ViewModelFactory
import com.example.mysubmissionjetpack1.ui.detail.DetailActivity
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel

class TvFavFragment : Fragment(), TvFavAdapter.OnItemClickCallback {

    private var _fragmentTvFavBinding: FragmentTvFavBinding? = null
    private val binding get() = _fragmentTvFavBinding
    private lateinit var viewModel: TvFavViewModel
    private lateinit var adapter: TvFavAdapter
    private var sort = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentTvFavBinding = FragmentTvFavBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!= null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvFavViewModel::class.java]

            adapter = TvFavAdapter(this)
            showLoading(true)

            with(binding?.rvTv) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
            getSeriesFavList(sort)
            sortSeries()
        }
    }

    override fun onItemClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_SHOW, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, DetailViewModel.TV_SERIES)

        context?.startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.rvTv?.visibility = View.INVISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
            binding?.rvTv?.visibility = View.VISIBLE
        }
    }

    private fun getSeriesFavList(sort: String) {
        viewModel.getSeriesFav(sort).observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.submitList(it)
                showLoading(false)
            }
        })
    }

    private fun sortSeries() {
        binding?.serNewest?.setOnClickListener{
            getSeriesFavList(SortUtil.NEWEST)
        }
        binding?.serOldest?.setOnClickListener{
            getSeriesFavList(SortUtil.OLDEST)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvFavBinding = null
    }

}