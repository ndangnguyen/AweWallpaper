package com.ndn.awewallpaper.utils.widget.dialogManager

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_alert.*
import com.ndn.awewallpaper.R
import com.ndn.awewallpaper.utils.RxView
import com.ndn.awewallpaper.utils.StringUtils

class DialogAlert : DialogFragment() {
    private lateinit var compositeDisposable: CompositeDisposable
    var listener: OnButtonClickedListener? = null
    private var title: String? = ""
    private var message: String? = ""
    private var titleBtn: String? = ""
    private var bgColorBtn: Int = android.R.color.transparent
    private var colorBtn: Int = R.color.C_222222

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        compositeDisposable = CompositeDisposable()

        arguments?.let {
            title = it.getString(
                TITLE_EXTRA
            )
            message = it.getString(
                MESSAGE_EXTRA
            )
            titleBtn = it.getString(
                TITLE_BUTTON_EXTRA
            )
            bgColorBtn = it.getInt(
                BG_COLOR_BUTTON_EXTRA
            )
            colorBtn = it.getInt(
                COLOR_BUTTON_EXTRA
            )
        }

        return inflater.inflate(R.layout.dialog_alert, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)

        tvTitle.text = title
        tvContent.text = message
        if (StringUtils.isNotEmpty(titleBtn)) {
            btnPositive.text = titleBtn
        }
        if (StringUtils.isBlank(title)) {
            tvTitle.visibility = View.GONE
        }

        btnPositive.setBackgroundResource(bgColorBtn)
        btnPositive.setTextColor(ContextCompat.getColor(btnPositive.context, colorBtn))

        val actionDisposable = RxView.clicks(btnPositive)
            .subscribe {
                dismiss()
                listener?.onPositiveClicked()
            }
        compositeDisposable.add(actionDisposable)
    }

    override fun onDestroy() {
        this.compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val TITLE_EXTRA = "TITLE_EXTRA"
        private const val MESSAGE_EXTRA = "MESSAGE_EXTRA"
        private const val TITLE_BUTTON_EXTRA = "TITLE_BUTTON_EXTRA"
        private const val BG_COLOR_BUTTON_EXTRA = "BG_COLOR_BUTTON_EXTRA"
        private const val COLOR_BUTTON_EXTRA = "COLOR_BUTTON_EXTRA"

        fun newInstance(
            title: String,
            message: String,
            titleBtn: String,
            buttonBgColor: Int = android.R.color.transparent,
            buttonColor: Int = R.color.C_222222,
            listener: OnButtonClickedListener?
        ): DialogAlert {
            return DialogAlert().apply {
                arguments = Bundle().apply {
                    putString(
                        TITLE_EXTRA, title
                    )
                    putString(
                        MESSAGE_EXTRA, message
                    )
                    putString(
                        TITLE_BUTTON_EXTRA, titleBtn
                    )
                    putInt(
                        BG_COLOR_BUTTON_EXTRA, buttonBgColor
                    )
                    putInt(
                        COLOR_BUTTON_EXTRA, buttonColor
                    )
                }
                this.listener = listener
            }
        }

        interface OnButtonClickedListener {
            fun onPositiveClicked()
        }
    }
}