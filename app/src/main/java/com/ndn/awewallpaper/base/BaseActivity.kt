package com.ndn.awewallpaper.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.ndn.awewallpaper.App
import com.ndn.awewallpaper.data.source.remote.api.error.RetrofitException
import com.ndn.awewallpaper.data.source.repositories.TokenRepository
import com.ndn.awewallpaper.utils.Constants
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogAlert
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogConfirm
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogManager
import com.ndn.awewallpaper.utils.widget.dialogManager.DialogManagerImpl
import org.koin.android.ext.android.inject
import java.net.HttpURLConnection

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), BaseView {

    protected abstract val layoutID: Int
    protected abstract val viewModelx: VM

    private val compositeDisposable = CompositeDisposable()

    protected lateinit var dialogManager: DialogManager
    private val tokenRepository: TokenRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        dialogManager = DialogManagerImpl(this)
        initialize()
        onSubscribeObserver()
    }

    override fun onStart() {
        super.onStart()
        App.instance.setCurrentClass(javaClass)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showLoading(isShow: Boolean) {
        if (isShow) showLoading() else hideLoading()
    }

    override fun showLoading() {
        dialogManager.showLoading()
    }

    override fun hideLoading() {
        dialogManager.hideLoading()
    }


    override fun showAlertDialog(
        title: String, message: String,
        titleButton: String, listener: DialogAlert.Companion.OnButtonClickedListener?
    ) {
        dialogManager.showAlertDialog(title, message, titleButton, listener)
    }

    override fun showAlertDialog(
        title: String, message: String,
        titleButton: String, buttonBgColor: Int, buttonColor: Int,
        listener: DialogAlert.Companion.OnButtonClickedListener?
    ) {
        dialogManager.showAlertDialog(
            title, message, titleButton, buttonBgColor,
            buttonColor, listener
        )
    }

    override fun showConfirmDialog(
        title: String?, message: String?,
        titleButtonPositive: String, titleButtonNegative: String,
        listener: DialogConfirm.OnButtonClickedListener?
    ) {
        dialogManager.showConfirmDialog(
            title, message, titleButtonPositive, titleButtonNegative,
            listener
        )
    }

    override fun handleApiError(apiError: Throwable) {
        if (apiError is RetrofitException) {
            if (apiError.getErrorCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                apiError.getMsgError()?.let {
                    showAlertDialog(message = it,
                        listener = object : DialogAlert.Companion.OnButtonClickedListener {
                            override fun onPositiveClicked() {
                                reLogin()
                            }
                        })
                }
                return
            }
            if (apiError.getErrorCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                apiError.getMsgError()?.let {
                    showAlertDialog(message = it)
                }
                return
            }
            apiError.getMsgError()?.let {
                showAlertDialog(message = it)
            }
        }
    }

    override fun launchRx(job: () -> Disposable) {
        compositeDisposable.add(job())
    }


    fun reLogin() {
        tokenRepository.doLogout()
        val bundle = Bundle()
        bundle.putString(Constants.EXTRA_RE_LOGIN, Constants.EXTRA_RE_LOGIN)
        // TODO uncomment later
        //  startActivityAtRoot(LoginActivity::class, bundle)
    }

    protected abstract fun initialize()

    protected abstract fun onSubscribeObserver()

}
