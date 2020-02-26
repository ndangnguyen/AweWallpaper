package com.ndn.awewallpaper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogAlert
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogConfirm
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel>(private val clazz: KClass<VM>)  : Fragment(), BaseView {

    protected abstract val layoutID: Int
    val compositeDisposable = CompositeDisposable()

    protected val viewModelx: VM by viewModel(clazz)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        onSubscribeObserver()
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
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

    protected abstract fun initialize()

    protected abstract fun onSubscribeObserver()
}