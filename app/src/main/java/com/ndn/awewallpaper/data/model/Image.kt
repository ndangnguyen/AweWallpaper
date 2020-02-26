package com.ndn.awewallpaper.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ndn.awewallpaper.utils.Constants.DEFAULT_INT
import com.ndn.awewallpaper.utils.Constants.DEFAULT_STRING
import kotlinx.android.parcel.Parcelize

/**
 * Copyright © 2020 Neolab VN.
 * Created by NguyenDan on 2020-02-26.
 */

@Parcelize
data class Image(
    @Expose
    @SerializedName("id")
    var id: String? = null,
    @Expose
    @SerializedName("width")
    var width: Int? = null,
    @Expose
    @SerializedName("height")
    var height: Int? = null,
    @Expose
    @SerializedName("file_size")
    var fileSize: Int? = null,
    @Expose
    @SerializedName("url_image")
    var urlImage: String? = null,
    @Expose
    @SerializedName("url_thumb")
    var urlThumb: String? = null,
    @Expose
    @SerializedName("url_page")
    var urlPage: String? = null
): Parcelable