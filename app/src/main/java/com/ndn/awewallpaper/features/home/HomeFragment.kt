package com.ndn.awewallpaper.features.home

import androidx.lifecycle.Observer
import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.base.BaseFragment
import com.ndn.awewallpaper.data.model.Image
import com.ndn.awewallpaper.features.home.banner.BannerFragment
import com.ndn.awewallpaper.features.home.banner.BannerViewPagerAdapter
import com.ndn.awewallpaper.utils.RandomUtils
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Copyright © 2020 Neolab VN.
 * Created by NguyenDan on 2020-02-26.
 */

class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {
    override val layoutID: Int
        get() = R.layout.fragment_home

    override fun initialize() {
        // Fetch data 5 banner
        viewModelx.fetchBanner()
    }

    override fun onSubscribeObserver() {
        with(viewModelx) {
            onFetchBannerSuccess.observe(viewLifecycleOwner, Observer {
                setupBannerUi(it)
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                showLoading(it)
            })
            onError.observe(viewLifecycleOwner, Observer {
                handleApiError(it)
            })
        }
    }

    private fun setupBannerUi(bannerList: MutableList<Image>) {
        val listBannerFragment = mutableListOf<BannerFragment>()
        // Add banner
        val startId = RandomUtils.generate(0, bannerList.size)

        for (i in startId until startId + NUMBER_BANNER)
            listBannerFragment.add(BannerFragment.newInstance(image = bannerList[i]))
        vpBanner.adapter = BannerViewPagerAdapter(
            childFragmentManager, listFragments = listBannerFragment
        )
        tlBanner.setupWithViewPager(vpBanner)
    }

    companion object {
        private const val NUMBER_BANNER = 5
    }
}