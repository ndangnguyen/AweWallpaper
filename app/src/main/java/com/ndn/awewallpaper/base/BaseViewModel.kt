package com.ndn.awewallpaper.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.ndn.awewallpaper.utils.liveData.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val isLoading = SingleLiveEvent<Boolean>()
    val onError = SingleLiveEvent<Throwable>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun launchRx(job: () -> Disposable) {
        compositeDisposable.add(job())
    }
}