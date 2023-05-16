package com.trunnghieu.tplogistic.ui.base

import com.trunnghieu.tplogistic.data.repository.remote.BaseRepo
import com.trunnghieu.tplogistic.ui.base.viewmodel.BaseViewModel

abstract class BaseRepoViewModel<T : BaseRepo, V : BaseUserView> : BaseViewModel() {

    protected abstract fun createRepo(): T

    var repo: T? = null
        get() {
            if (field == null) {
                field = createRepo()
            }
            return field
        }

    protected var uiCallback: V? = null

    fun init(uiCallback: V) {
        this.uiCallback = uiCallback
    }
}
