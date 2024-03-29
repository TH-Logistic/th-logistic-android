package com.trunnghieu.tplogistic.ui.screens.reset_password

import androidx.lifecycle.MutableLiveData
import com.trunnghieu.tplogistic.data.repository.remote.account.AccountRepo
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel

class ResetPasswordVM : BaseRepoViewModel<AccountRepo, ResetPasswordUV>() {


    override fun createRepo(): AccountRepo {
        return AccountRepo()
    }

    val userId = MutableLiveData("")
    val errorMessage = MutableLiveData("")
    val alreadyRequestResetPassword = MutableLiveData(false)

    /**
     * On back pressed
     */
    fun backPress() {
        if (alreadyRequestResetPassword.value == true) {
            alreadyRequestResetPassword.value = false
            return
        }
        uiCallback?.onBackPressed()
    }

    /**
     * Validate password & make a request reset password api
     */
    fun validateAndRequestResetPassword() {
        errorMessage.value = ""
        uiCallback?.resetValidate()
        val phoneNumber = userId.value
        if (phoneNumber?.isEmpty() == true) {
            uiCallback?.phoneNumberIsEmpty()
            return
        }

        if (alreadyRequestResetPassword.value == false) {
            requestResetPassword()
        } else {
            // Back to Login screen
            uiCallback?.onBackPressed()
        }
    }

    /**
     * Request reset password
     */
    fun requestResetPassword() {

    }
}
