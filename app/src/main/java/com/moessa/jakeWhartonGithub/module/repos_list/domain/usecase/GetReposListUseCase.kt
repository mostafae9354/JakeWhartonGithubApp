package com.moessa.jakeWhartonGithub.module.repos_list.domain.usecase

import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.RepoEntity
import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.param.GetReposListParam
import com.moessa.jakeWhartonGithub.module.repos_list.domain.repository.ReposRepository
import io.reactivex.Single
import javax.inject.Inject

class GetReposListUseCase @Inject constructor(private val repository: ReposRepository) {
    operator fun invoke(param: GetReposListParam): Single<List<RepoEntity>> {
        return with(param) {
            repository.getReposList(page, per_page)
        }
    }
}