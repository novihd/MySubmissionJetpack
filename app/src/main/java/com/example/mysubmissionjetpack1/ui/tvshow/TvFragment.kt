package com.example.mysubmissionjetpack1.ui.tvshow

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.databinding.FragmentTvBinding
import com.example.mysubmissionjetpack1.ui.ViewModelFactory
import com.example.mysubmissionjetpack1.ui.detail.DetailActivity
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.TV_SERIES
import com.example.mysubmissionjetpack1.vo.Status

class TvFragment : Fragment(), TvAdapter.OnItemClickCallback {

    private lateinit var fragmentTvBinding: FragmentTvBinding
    private lateinit var tvAdapter: TvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            viewModel.getSeries().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> showLoading(false)
                        Status.SUCCESS -> {
                            showSeries(it.data)
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

    private fun showSeries(list: PagedList<TvEntity>?) {
        tvAdapter = TvAdapter(this)
        tvAdapter.submitList(list)
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            2
        } else {
            3
        }

        with(fragmentTvBinding.rvTv) {
            layoutManager = GridLayoutManager(context, spanCount)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }

    override fun onItemClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_SHOW, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SERIES)
        context?.startActivity(intent)
    }

    private fun showLoading(state:Boolean) {
        fragmentTvBinding.apply {
            if (state) {
                rvTv.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            } else {
                rvTv.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        }
    }
}