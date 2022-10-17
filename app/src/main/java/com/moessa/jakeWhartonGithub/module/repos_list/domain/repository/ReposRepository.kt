package com.moessa.jakeWhartonGithub.module.repos_list.domain.repository

import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.RepoEntity
import io.reactivex.Single

interface ReposRepository {
    fun getReposList(page: Int, limit: Int): Single<List<RepoEntity>>
}