package com.trunnghieu.tplogistic.ui.base.viewmodel

import com.trunnghieu.tplogistic.ui.base.BaseUserView

abstract class BaseUiViewModel<V: BaseUserView>: BaseViewModel() {

    protected var uiCallback: V? = null

    fun init(uiCallback: V) {
        this.uiCallback = uiCallback
    }

}
