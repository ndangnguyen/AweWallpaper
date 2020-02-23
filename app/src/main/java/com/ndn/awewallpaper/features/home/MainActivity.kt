package com.ndn.awewallpaper.features.home

import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainViewModel>() {

    override val layoutID: Int
        get() = R.layout.activity_home

    override val viewModelx: MainViewModel by viewModel()

    override fun initialize() {
    }

    override fun onSubscribeObserver() {

    }

}
