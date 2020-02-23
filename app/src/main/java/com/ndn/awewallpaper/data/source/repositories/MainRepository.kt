package com.ndn.awewallpaper.data.source.repositories

import com.ndn.awewallpaper.data.source.remote.AweWallpaperApi
import com.ndn.awewallpaper.data.source.local.sharedprf.SharedPrefs

interface MainRepository {

}

class MainRepositoryImpl(
    private val aweWallpaperApi: AweWallpaperApi,
    private val sharedPrefs: SharedPrefs
) : MainRepository {

}
