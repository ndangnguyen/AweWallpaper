package com.ndn.awewallpaper.utils

import android.content.Context
import com.ndn.awewallpaper.R

object ValidateUtils {

    fun validateStoreId(storeID: String, context: Context): String {
        if (storeID.isBlank()) {
            return context.getString(R.string.err_storeId_required)
        }
        return ""
    }

    fun validateSecurityCode(code: String, context: Context): String {
        if (code.isEmpty()) {
            return context.getString(R.string.err_security_code_required)
        }
        if (code.contains(" ")) {
            return context.getString(R.string.enter_only_letters_and_numbers)
        }
        return ""
    }

}