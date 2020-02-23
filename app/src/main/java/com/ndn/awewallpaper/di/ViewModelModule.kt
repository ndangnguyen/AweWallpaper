package com.ndn.awewallpaper.di

import com.ndn.awewallpaper.features.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel() }
}