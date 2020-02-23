package com.ndn.awewallpaper.utils.widget.dialogManager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.ndn.awewallpaper.R
import java.lang.ref.WeakReference

class DialogManagerImpl(ctx: Context?) : DialogManager {

    private var context: WeakReference<Context?>? = null
    private var progressDialog: ProgressDialog? = null

    init {
        context = WeakReference(ctx).apply {
            progressDialog = ProgressDialog(this.get()!!)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun showProcessing() {
        progressDialog?.show()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
    }

    override fun onRelease() {
        progressDialog = null
    }

    override fun showAlertDialog(
        title: String, message: String, titleButton: String,
        listener: DialogAlert.Companion.OnButtonClickedListener?
    ) {
        val dialog = DialogAlert.newInstance(
            title, message, titleButton, 0, R.color.C_222222,
            listener
        )
        val fm = (context?.get() as AppCompatActivity).supportFragmentManager
        dialog.show(fm, DialogAlert::class.java.simpleName)
    }

    override fun showAlertDialog(
        title: String, message: String, titleButton: String,
        buttonBgColor: Int, buttonColor: Int,
        listener: DialogAlert.Companion.OnButtonClickedListener?
    ) {
        val dialog = DialogAlert.newInstance(
            title, message, titleButton, buttonBgColor,
            buttonColor, listener
        )
        val fm = (context?.get() as AppCompatActivity).supportFragmentManager
        dialog.show(fm, DialogAlert::class.java.simpleName)
    }


    override fun showConfirmDialog(
        title: String?, message: String?,
        titleButtonPositive: String, titleButtonNegative: String,
        listener: DialogConfirm.OnButtonClickedListener?
    ) {
        val dialog = DialogConfirm.newInstance(
            title, message, titleButtonPositive,
            titleButtonNegative, listener
        )
        val fm = (context?.get() as AppCompatActivity).supportFragmentManager
        dialog.show(fm, DialogAlert::class.java.simpleName)
    }

    companion object {
        const val TAG = "DialogManagerImpl"
    }

}