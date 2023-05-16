package com.trunnghieu.tplogistic.ui.base.activity

import androidx.databinding.ViewDataBinding
import com.trunnghieu.tplogistic.ui.base.viewmodel.BaseViewModel
import com.trunnghieu.tplogistic.utils.helper.SystemHelper

abstract class BaseFullscreenActivity<T : ViewDataBinding, VM : BaseViewModel> :
    BaseActivity<T, VM>() {

    private val runInFullScreen = false

    override fun onStart() {
        super.onStart()
        if (runInFullScreen) {
            SystemHelper.hideSystemUI(window)
        }
    }
}
