package com.ndn.awewallpaper.features.home.banner

import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.base.BaseFragment
import com.ndn.awewallpaper.data.model.Image
import com.ndn.awewallpaper.utils.Constants.EXTRAS_IMAGE
import com.ndn.awewallpaper.utils.extension.loadImageUrl
import com.ndn.awewallpaper.utils.extension.withArgs
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment : BaseFragment<BannerViewModel>(BannerViewModel::class) {
    override val layoutID: Int
        get() = R.layout.fragment_banner

    override fun initialize() {
        val image = arguments?.getParcelable<Image>(EXTRAS_IMAGE)
        setUi(image)
    }

    override fun onSubscribeObserver() {
    }

    private fun setUi(image: Image?) {
        ivImage.loadImageUrl(image?.urlImage)
        tvName.text = image?.fileSize.toString()
    }

    companion object {
        fun newInstance(image: Image): BannerFragment {
            return BannerFragment().withArgs {
                putParcelable(EXTRAS_IMAGE, image)
            }
        }
    }
}
