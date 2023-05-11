package com.trunnghieu.tplogisticsapplication.ui.screens.login

import android.Manifest
import androidx.lifecycle.*
import com.trunnghieu.tplogisticsapplication.data.preferences.AppPrefs
import com.trunnghieu.tplogisticsapplication.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.AccountRepo
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.info.AccountInfoResponse
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login.LoginDTO
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login.LoginResponse
import com.trunnghieu.tplogisticsapplication.ui.base.BaseRepoViewModel
import com.trunnghieu.tplogisticsapplication.utils.helper.AppPreferences


class LoginVM : BaseRepoViewModel<AccountRepo, LoginUV>() {

    override fun createRepo(): AccountRepo {
        return AccountRepo()
    }

    val appVersion = MutableLiveData("1.0.0")

    fun initLifeCycle(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    private val permissions = mutableListOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val locationPermissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val appPrefs = AppPreferences.get()

    // Account data
    private val savedPhoneNumber = appPrefs.getString(AppPrefs.LOGIN.PHONE_NUMBER)
    private val savedPassword = appPrefs.getString(AppPrefs.LOGIN.PASSWORD)

    var alreadyRestoreLoginData = false
    private var isLoggedIn = false

    val phoneNumber = MutableLiveData("")
    val password = MutableLiveData("")
    val rememberMe = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    fun checkAutoLogin() {
        if (appPrefs.getBoolean(AppPrefs.LOGIN.IS_LOGIN)) {
            phoneNumber.value = appPrefs.getString(AppPrefs.LOGIN.PHONE_NUMBER)
            password.value = appPrefs.getString(AppPrefs.LOGIN.PASSWORD)

            requestLogin(phoneNumber.value, password.value)
        }
    }

    /**
     * Continue login flow from login data
     */
    fun continueLoginFlow() {
        alreadyRestoreLoginData = false
        if (isLoggedIn) {
            // Show app
            requestLogin(savedPhoneNumber, savedPassword)
        }
    }

    private fun askPermissions() {
        uiCallback?.askPermissions(permissions)
    }

    /**
     * Request location permission
     */
    fun requestLocationPermission() {
        uiCallback?.askLocationPermissions(locationPermissions)
    }

    /**
     * Go to Reset Password screen
     */
    fun goToResetPassword() {
        uiCallback?.goToResetPassword()
    }

    /**
     * Request login when touch on Login button
     */
    fun login() {
        requestLogin(phoneNumber.value, password.value)
    }

    private fun requestLogin(phoneNumber: String? = null, password: String? = null) {
        errorMessage.value = ""
        uiCallback?.resetValidate()

        val phoneNumberRequest = (
                if (phoneNumber.isNullOrEmpty())
                    this.phoneNumber.value
                else
                    phoneNumber
                )?.trim()
        val passwordRequest = if (password.isNullOrEmpty()) this.password.value else password

        if (phoneNumberRequest?.isEmpty() == true
            && passwordRequest?.isEmpty() == true
        ) {
            uiCallback?.phoneNumberAndPasswordIsEmpty()
            return
        }

        if (phoneNumberRequest?.isEmpty() == true) {
            uiCallback?.phoneNumberIsEmpty()
            return
        }
        if (passwordRequest?.isEmpty() == true) {
            uiCallback?.passwordIsEmpty()
            return
        }

        showLoading(true)

        val loginDto = LoginDTO(
            phoneNumberRequest!!,
            passwordRequest!!,
        )
        repo?.requestLogin(
            loginDto,
            object : AccountRepo.PasswordCallback<LoginResponse> {
                override fun apiResponse(data: LoginResponse) {
                    if (rememberMe.value == true) {
                        appPrefs.storeValue(AppPrefs.LOGIN.IS_LOGIN, true)
                        appPrefs.storeValue(AppPrefs.LOGIN.PHONE_NUMBER, phoneNumberRequest)
                        appPrefs.storeValue(AppPrefs.LOGIN.PASSWORD, passwordRequest)
                    }
                    appPrefs.storeValue(AppPrefs.LOGIN.ACCESS_TOKEN, data.accessToken)


                    getUserData()
                }

                override fun invalidLoginInfo() {
                    uiCallback?.invalidLoginInfo()
                }

                override fun connectionError() {
                    uiCallback?.connectionError()
                }

                override fun showMessage(message: String?) {
                    showError(message)
                }
            }
        )
    }

    private fun getUserData() {
        repo?.getAccountInfo(object : BaseRepoCallback<AccountInfoResponse> {
            override fun apiResponse(data: AccountInfoResponse) {
                appPrefs.storeValue(AppPrefs.DRIVER.FULL_NAME, data.name)
                appPrefs.storeValue(AppPrefs.DRIVER.MOBILE_NO, data.phoneNumber)
                appPrefs.storeValue(AppPrefs.DRIVER.AVATAR_URL, data.avatar)
                uiCallback?.goToMain()
            }
        })
    }

    /**
     * Show language selection popup
     */
    fun showLanguagePopup() {
        uiCallback?.showLanguagePopup()
    }

}
