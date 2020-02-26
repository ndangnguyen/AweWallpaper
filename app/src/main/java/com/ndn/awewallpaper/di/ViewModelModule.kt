package com.ndn.awewallpaper.di

import com.ndn.awewallpaper.features.MainViewModel
import com.ndn.awewallpaper.features.home.HomeViewModel
import com.ndn.awewallpaper.features.home.banner.BannerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { BannerViewModel() }
}