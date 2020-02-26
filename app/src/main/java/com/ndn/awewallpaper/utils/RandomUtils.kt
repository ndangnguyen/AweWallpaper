package com.ndn.awewallpaper.utils

/**
 * Copyright © 2020 Neolab VN.
 * Created by NguyenDan on 2020-02-26.
 */

object RandomUtils {
    fun generate(min: Int, max: Int): Int {
        return min + (Math.random() * (max - min + 1)).toInt()
    }
}