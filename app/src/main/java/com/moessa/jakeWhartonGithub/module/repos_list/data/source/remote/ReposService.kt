package com.moessa.jakeWhartonGithub.module.repos_list.data.source.remote

import com.moessa.jakeWhartonGithub.module.repos_list.data.model.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {
    @GET("repos")
    fun getReposList(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Single<List<RepoModel>>
}