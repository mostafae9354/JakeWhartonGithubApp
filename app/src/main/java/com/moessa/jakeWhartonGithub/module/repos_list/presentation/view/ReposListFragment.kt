package com.moessa.jakeWhartonGithub.module.repos_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moessa.jakeWhartonGithub.R
import com.moessa.jakeWhartonGithub.core.utils.PaginationScrollListener
import com.moessa.jakeWhartonGithub.databinding.FragmentReposListBinding
import com.moessa.jakeWhartonGithub.module.repos_list.presentation.adapter.ReposAdapter
import com.moessa.jakeWhartonGithub.module.repos_list.presentation.uimodel.mapper.toUiModel
import com.moessa.jakeWhartonGithub.module.repos_list.presentation.viewmodel.ReposViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposListFragment : Fragment() {

    private lateinit var binding: FragmentReposListBinding
    private val viewModel by viewModels<ReposViewModel>()
    private lateinit var adapter: ReposAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repos_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupUI()
        loadMoreData()
    }

    private fun setupUI() {
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.setHasFixedSize(true)
        adapter = ReposAdapter()
        binding.rvRepos.addItemDecoration(
            DividerItemDecoration(
                binding.rvRepos.context,
                (binding.rvRepos.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvRepos.adapter = adapter

        binding.rvRepos.addOnScrollListener(object :
            PaginationScrollListener(binding.rvRepos.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                loadMoreData()
            }
        })
    }

    private fun observeViewModel() {

        viewModel.reposListLiveData().observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty())
                binding.rvRepos.visibility = View.VISIBLE
            adapter.submitList(ArrayList(list.map { it.toUiModel() }))
        }

        viewModel.errorLiveData().observe(viewLifecycleOwner) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
            it.printStackTrace()
        }

        viewModel.loadingLiveData().observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun loadMoreData() {

        viewModel.getMoreRepos()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReposListFragment()
    }
}