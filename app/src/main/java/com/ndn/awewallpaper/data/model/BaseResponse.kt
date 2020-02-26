package com.ndn.awewallpaper.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Copyright © 2020 Neolab VN.
 * Created by NguyenDan on 2020-02-26.
 */

data class BaseResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean? = null,
    @Expose
    @SerializedName("wallpapers")
    val wallpapers: MutableList<Image>? = null
)