package com.ndn.awewallpaper.features.home.banner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ndn.awewallpaper.features.home.banner.BannerFragment

/**
 * Copyright © 2020 Neolab VN.
 * Created by NguyenDan on 2020-02-26.
 */

class BannerViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val listFragments: MutableList<BannerFragment>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return listFragments[position]
    }

    override fun getCount(): Int {
        return listFragments.size
    }
}