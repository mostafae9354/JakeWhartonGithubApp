package com.moessa.jakeWhartonGithub.module.repos_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.moessa.jakeWhartonGithub.core.base.BaseViewModel
import com.moessa.jakeWhartonGithub.core.di.qualifier.IoScheduler
import com.moessa.jakeWhartonGithub.core.di.qualifier.MainScheduler
import com.moessa.jakeWhartonGithub.core.livedata.SingleLiveEvent
import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.RepoEntity
import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.param.GetReposListParam
import com.moessa.jakeWhartonGithub.module.repos_list.domain.usecase.GetReposListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val getReposListUseCase: GetReposListUseCase,
    @MainScheduler private val mainScheduler: Scheduler,
    @IoScheduler private val ioScheduler: Scheduler
) : BaseViewModel() {

    private var page = 0
    private val perPage = 15

    var isLoading = false
    var isLastPage = false
    private val reposList = arrayListOf<RepoEntity>()

    private val _reposListLiveData = SingleLiveEvent<List<RepoEntity>>()
    fun reposListLiveData(): LiveData<List<RepoEntity>> = _reposListLiveData

    private val _errorLiveData = SingleLiveEvent<Throwable>()
    fun errorLiveData(): LiveData<Throwable> = _errorLiveData

    private val _loadingLiveData = SingleLiveEvent<Boolean>()
    fun loadingLiveData(): LiveData<Boolean> = _loadingLiveData

    fun getMoreRepos() {
        getReposListUseCase(GetReposListParam(++page, perPage))
            .doOnSubscribe {
                isLoading = true
                _loadingLiveData.postValue(true)
            }
            .doOnTerminate {
                isLoading = false
                _loadingLiveData.postValue(false)
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(::onGetReposSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onGetReposSuccess(repos: List<RepoEntity>) {

        if (repos.size < perPage)
            isLastPage = true
        reposList.addAll(repos)
        _reposListLiveData.value = reposList
    }

    //TODO Map Backend Domain Exception
    private fun onError(error: Throwable) {
        _errorLiveData.value = error
    }
}