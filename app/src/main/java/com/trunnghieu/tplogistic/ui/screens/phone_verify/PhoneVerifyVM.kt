package com.trunnghieu.tplogistic.ui.screens.phone_verify

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.trunnghieu.tplogistic.data.repository.remote.account.AccountRepo
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel
import kotlinx.coroutines.*

class PhoneVerifyVM : BaseRepoViewModel<AccountRepo, PhoneVerifyUV>() {

    override fun createRepo(): AccountRepo {
        return AccountRepo()
    }

    var newPhoneNumber = ""
    var expiresTime: Long = 5
    val otpIncorrect = MutableLiveData(false)
    val timeRemaining = MutableLiveData("05:00")
    val otpTimeOut = MutableLiveData(false)
    var otpCode = MutableLiveData("")

    private var countDownJob: Job? = null

    /**
     * Init lifecycle for parent view
     */
    fun initLifecycle(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    /**
     * Get extras from bundle
     */
    fun getExtras(arguments: Bundle?) {
        arguments?.let {
            newPhoneNumber = it.getString(PhoneVerifyFragment.ARG_PHONE_NUMBER) ?: ""
            expiresTime = it.getLong(PhoneVerifyFragment.ARG_EXPIRES_TIME, 5)
        }
        startCountDown()
    }

    /**
     * On back pressed
     */
    fun backPress() {
        uiCallback?.onFragmentBackPressed()
    }

    /**
     * Request OTP code
     */
    fun requestOtp() {

    }

    /**
     * Verify OTP from user
     */
    fun verifyOtp() {

    }

    private fun updateAccountInfo() {
        // Confirm OTP done -> Update phone number

    }

    /**
     * Start countdown for waiting for OTP code
     */
    private fun startCountDown() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun stopCountDown() {
        countDownJob?.cancel()
        countDownJob = null
        otpTimeOut.value = true
        timeRemaining.value = "00:00"
    }
}
