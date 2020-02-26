package com.ndn.awewallpaper.features

import androidx.core.view.GravityCompat
import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.base.BaseActivity
import com.ndn.awewallpaper.features.home.HomeFragment
import com.ndn.awewallpaper.utils.extension.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainViewModel>() {

    override val layoutID: Int
        get() = R.layout.activity_main

    override val viewModelx: MainViewModel by viewModel()

    override fun initialize() {
        ivMenu.setOnClickListener {
            dlMain.openDrawer(GravityCompat.START)
        }
        replaceFragmentInActivity(R.id.flMainContainer, HomeFragment(), false)
    }

    override fun onSubscribeObserver() {

    }
}
