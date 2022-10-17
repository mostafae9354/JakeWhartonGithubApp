package com.moessa.jakeWhartonGithub.module.repos_list.di

import com.moessa.jakeWhartonGithub.module.repos_list.data.repository.ReposRepositoryImpl
import com.moessa.jakeWhartonGithub.module.repos_list.data.source.remote.ReposService
import com.moessa.jakeWhartonGithub.module.repos_list.domain.repository.ReposRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposModule {

    companion object {
        @Provides
        @ViewModelScoped
        fun provideReposService(retrofit: Retrofit): ReposService =
            retrofit.create(ReposService::class.java)
    }

    @Binds
    @ViewModelScoped
    abstract fun bindReposRepository(impl: ReposRepositoryImpl): ReposRepository
}