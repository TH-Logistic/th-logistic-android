package com.trunnghieu.tplogistic.ui.screens.account_settings

import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface AccountSettingsUV : BaseUserView {
    fun onFragmentBackPressed()
    fun goToChangePassword()
    fun goToVerifyPhone(newPhoneNumber: String, expiresTimeInMinutes: Int)
    fun pickImageFromGallery()
    fun showProgress(show: Boolean)
    fun progressUpdate(progress: Int)
    fun uploadAvatarSuccess(avatarUrl: String)
    fun uploadAvatarFail()
}
