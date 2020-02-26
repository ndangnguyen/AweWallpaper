package com.ndn.awewallpaper.data.source.remote

import com.ndn.awewallpaper.data.model.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET


/**
 * REST API access points
 */
interface AweWallpaperApi {

    @GET("get.php?method=by_views")
    fun fetchBanner(): Single<BaseResponse>

}
