package com.trunnghieu.tplogistic.ui.screens.account_settings

import androidx.lifecycle.MutableLiveData
import com.trunnghieu.tplogistic.data.preferences.AppPrefs
import com.trunnghieu.tplogistic.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogistic.data.repository.remote.account.AccountRepo
import com.trunnghieu.tplogistic.data.repository.remote.account.info.AccountInfoResponse
import com.trunnghieu.tplogistic.data.repository.remote.account.update_account.UpdateAccountInfoDto
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel
import com.trunnghieu.tplogistic.utils.helper.AppPreferences
import java.io.File

class AccountSettingsVM : BaseRepoViewModel<AccountRepo, AccountSettingsUV>() {

    override fun createRepo(): AccountRepo {
        return AccountRepo()
    }

    private val appPrefs = AppPreferences.get()

    val isEditingProfile = MutableLiveData(false)
    val phoneNumber = MutableLiveData(appPrefs.getString(AppPrefs.DRIVER.MOBILE_NO))
    val driverName = MutableLiveData(appPrefs.getString(AppPrefs.DRIVER.FULL_NAME))

    /**
     * On back pressed
     */
    fun backPress() {
        uiCallback?.onFragmentBackPressed()
    }

    /**
     * Go to Change password screen
     */
    fun changePassword() {
        uiCallback?.goToChangePassword()
    }

    /**
     * Select avatar from gallery
     */
    fun selectAvatar() {
        uiCallback?.pickImageFromGallery()
    }

    /**
     * Edit user profile
     */
    fun editProfile() {
        isEditingProfile.value = true
    }

    /**
     * Get account info
     */
    fun getDriverInfo() {

    }

    /**
     * Save new profile
     */
    fun saveProfile() {
        val bodyRequest = UpdateAccountInfoDto(
            phoneNumber.value!!,
            driverName.value!!,
        )

        showLoading(true)
        repo?.updateAccountInfo(
            appPrefs.getString(AppPrefs.DRIVER.ID),
            bodyRequest,
            callback = object : BaseRepoCallback<AccountInfoResponse> {
                override fun apiResponse(data: AccountInfoResponse) {
                    showLoading(false)

                    println("justin: $data")

                    phoneNumber.value = data.phoneNumber
                    driverName.value = data.name

                    appPrefs.storeValue(AppPrefs.DRIVER.MOBILE_NO, data.phoneNumber)
                    appPrefs.storeValue(AppPrefs.DRIVER.FULL_NAME, data.name)

                    isEditingProfile.value = false
                }
            }
        )
    }

    /**
     * Start upload driver avatar
     */
    fun uploadDriverAvatar(selectedFile: File) {

    }
}
