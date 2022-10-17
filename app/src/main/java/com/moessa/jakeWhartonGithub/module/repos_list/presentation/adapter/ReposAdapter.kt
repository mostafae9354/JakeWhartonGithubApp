package com.moessa.jakeWhartonGithub.module.repos_list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moessa.jakeWhartonGithub.R
import com.moessa.jakeWhartonGithub.module.repos_list.presentation.uimodel.RepoUiModel

class ReposAdapter() : ListAdapter<RepoUiModel, ReposAdapter.DataViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RepoUiModel>() {
        override fun areItemsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvRepoName: AppCompatTextView = itemView.findViewById(R.id.tv_repo_name)
        private val tvRepoDesc: AppCompatTextView = itemView.findViewById(R.id.tv_repo_desc)

        fun bind(repoUiModel: RepoUiModel) {
            tvRepoName.text = repoUiModel.name
            tvRepoDesc.text = repoUiModel.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repo, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(getItem(position))
}