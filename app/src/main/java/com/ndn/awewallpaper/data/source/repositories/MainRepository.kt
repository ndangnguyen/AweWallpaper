package com.ndn.awewallpaper.data.source.repositories

import com.ndn.awewallpaper.data.model.BaseResponse
import com.ndn.awewallpaper.data.model.Image
import com.ndn.awewallpaper.data.source.remote.AweWallpaperApi
import com.ndn.awewallpaper.data.source.local.sharedprf.SharedPrefs
import io.reactivex.Single

interface MainRepository {
    fun fetchBanner(): Single<MutableList<Image>>
}

class MainRepositoryImpl(
    private val aweWallpaperApi: AweWallpaperApi,
    private val sharedPrefs: SharedPrefs
) : MainRepository {
    override fun fetchBanner(): Single<MutableList<Image>> {
        return aweWallpaperApi.fetchBanner().map { it.wallpapers }
    }
}
