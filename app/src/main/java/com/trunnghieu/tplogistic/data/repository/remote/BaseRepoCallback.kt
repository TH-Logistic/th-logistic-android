package com.trunnghieu.tplogistic.data.repository.remote

interface BaseRepoCallback<T> {
    fun apiRequesting(showLoading: Boolean) = Unit
    fun apiResponse(data: T)
    fun showMessage(message: String?) = Unit
    fun connectionError() = Unit
}
