package com.trunnghieu.tplogistic.ui.screens.change_password

import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface ChangePasswordUV : BaseUserView {
    fun onBackPressed()
    fun resetValidate()
    fun currentPasswordInCorrect()
    fun newPasswordNotMatch()
    fun newPasswordMustDifferentFromCurrentPassword()
    fun changePasswordSuccess(message: String = "")
}
