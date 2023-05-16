package com.trunnghieu.tplogistic.ui.screens.phone_verify

import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface PhoneVerifyUV : BaseUserView {
    fun onFragmentBackPressed()
    fun clearOtpCode()
    fun updatePhoneNumberSuccess()
}
