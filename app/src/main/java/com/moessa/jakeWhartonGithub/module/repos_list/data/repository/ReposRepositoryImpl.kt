package com.moessa.jakeWhartonGithub.module.repos_list.data.repository

import com.moessa.jakeWhartonGithub.module.repos_list.data.model.mapper.toEntity
import com.moessa.jakeWhartonGithub.module.repos_list.data.source.remote.ReposService
import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.RepoEntity
import com.moessa.jakeWhartonGithub.module.repos_list.domain.repository.ReposRepository
import io.reactivex.Single
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(private val service: ReposService) :
    ReposRepository {
    override fun getReposList(page: Int, limit: Int): Single<List<RepoEntity>> {
        return service.getReposList(page, limit).map { response ->
            response.map { it.toEntity() }
        }
    }
}