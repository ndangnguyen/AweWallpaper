package com.ndn.awewallpaper.base

import io.reactivex.disposables.Disposable
import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogAlert
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogConfirm


/**
 * @author LongHV.
 */

interface BaseView {

    fun showLoading(isShow: Boolean)
    fun showLoading()
    fun hideLoading()
    fun handleApiError(apiError: Throwable)
    fun launchRx(job: () -> Disposable)

    fun showAlertDialog(title: String = "", message: String = "", titleButton: String = "",
            listener: DialogAlert.Companion.OnButtonClickedListener? = null)

    fun showAlertDialog(title: String = "", message: String = "", titleButton: String = "",
                        buttonBgColor: Int = 0, buttonColor: Int = R.color.C_222222,
                        listener: DialogAlert.Companion.OnButtonClickedListener? = null)

    fun showConfirmDialog(title: String? = "", message: String? = "",
            titleButtonPositive: String = "", titleButtonNegative: String = "",
            listener: DialogConfirm.OnButtonClickedListener? = null)
}
