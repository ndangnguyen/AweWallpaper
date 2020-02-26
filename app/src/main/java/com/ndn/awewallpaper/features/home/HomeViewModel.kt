package com.ndn.awewallpaper.features.home

import com.ndn.awewallpaper.base.BaseViewModel
import com.ndn.awewallpaper.data.model.Image
import com.ndn.awewallpaper.data.source.repositories.MainRepository
import com.ndn.awewallpaper.utils.extension.async
import com.ndn.awewallpaper.utils.extension.loading
import com.ndn.awewallpaper.utils.liveData.SingleLiveEvent
import com.ndn.awewallpaper.utils.rxAndroid.BaseSchedulerProvider
import io.reactivex.rxkotlin.subscribeBy

class HomeViewModel(private val mainRepository: MainRepository,
                    private val baseSchedulerProvider: BaseSchedulerProvider) : BaseViewModel() {
    val onFetchBannerSuccess = SingleLiveEvent<MutableList<Image>>()

    fun fetchBanner() {
        launchRx {
            mainRepository.fetchBanner()
                .async(baseSchedulerProvider)
                .loading(isLoading)
                .subscribeBy(
                    onSuccess = {
                        onFetchBannerSuccess.value = it
                    },
                    onError = {
                        onError.value = it
                    }
                )
        }
    }
}
