package com.ndn.awewallpaper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogAlert
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogConfirm

abstract class BaseDialogFragment<VM : ViewModel> : DialogFragment(), BaseView {

    protected abstract val layoutID: Int
    val compositeDisposable = CompositeDisposable()
    protected abstract val viewModelx: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        onSubscribeObserver()
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
            it.setCanceledOnTouchOutside(false)
            it.setCancelable(false)
        }
    }

    override fun onDetach() {
        compositeDisposable.clear()
        super.onDetach()
    }

    override fun showLoading(isShow: Boolean) {
        if (isShow) showLoading() else hideLoading()
    }

    override fun showLoading() {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).showLoading()
    }

    override fun hideLoading() {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).hideLoading()
    }

    override fun handleApiError(apiError: Throwable) {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).handleApiError(
            apiError)
    }

    override fun showAlertDialog(title: String, message: String,
                                 titleButton: String, listener: DialogAlert.Companion.OnButtonClickedListener?) {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).showAlertDialog(title,
            message, titleButton, listener)
    }

    override fun showAlertDialog(title: String, message: String,
                                 titleButton: String, buttonBgColor: Int, buttonColor: Int,
                                 listener: DialogAlert.Companion.OnButtonClickedListener?) {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).showAlertDialog(title,
            message, titleButton, buttonBgColor, buttonColor, listener)
    }

    override fun showConfirmDialog(title: String?, message: String?,
                                   titleButtonPositive: String, titleButtonNegative: String,
                                   listener: DialogConfirm.OnButtonClickedListener?) {
        if (activity is BaseActivity<*>) (activity as BaseActivity<*>).showConfirmDialog(
            title,
            message, titleButtonPositive, titleButtonNegative, listener)
    }

    override fun launchRx(job: () -> Disposable) {
        compositeDisposable.add(job())
    }

    abstract fun initialize()
    abstract fun onSubscribeObserver()
}