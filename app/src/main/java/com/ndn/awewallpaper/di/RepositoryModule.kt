package com.ndn.awewallpaper.di

import android.app.Application
import com.ndn.awewallpaper.data.source.remote.AweWallpaperApi
import com.ndn.awewallpaper.data.source.local.sharedprf.SharedPrefs
import com.ndn.awewallpaper.data.source.local.sharedprf.SharedPrefsImpl
import com.ndn.awewallpaper.data.source.repositories.MainRepository
import com.ndn.awewallpaper.data.source.repositories.MainRepositoryImpl
import com.ndn.awewallpaper.data.source.repositories.TokenRepository
import com.ndn.awewallpaper.data.source.repositories.TokenRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val repositoryModule = module {
    single { provideTokenRepository(androidApplication()) }
    single { provideMainRepository(get(), get()) }
}

fun provideTokenRepository(app: Application): TokenRepository {
    return TokenRepositoryImpl(
            SharedPrefsImpl(app)
    )
}

fun provideMainRepository(api: AweWallpaperApi, sharedPrefs: SharedPrefs): MainRepository = MainRepositoryImpl(api, sharedPrefs)
