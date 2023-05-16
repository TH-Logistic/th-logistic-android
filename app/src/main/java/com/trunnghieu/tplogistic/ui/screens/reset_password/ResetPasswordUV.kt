package com.trunnghieu.tplogistic.ui.screens.reset_password

import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface ResetPasswordUV : BaseUserView {
    fun onBackPressed()
    fun resetValidate()
    fun phoneNumberIsEmpty()
}
