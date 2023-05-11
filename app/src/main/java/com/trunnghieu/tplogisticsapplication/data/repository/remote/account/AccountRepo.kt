package com.trunnghieu.tplogisticsapplication.data.repository.remote.account

import com.trunnghieu.tplogisticsapplication.data.repository.remote.BaseRepo
import com.trunnghieu.tplogisticsapplication.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogisticsapplication.data.repository.remote.BaseResponse
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.info.AccountInfoResponse
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login.LoginDTO
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.UnknownHostException

class AccountRepo : BaseRepo() {

    /**
     * Request login
     */
    fun requestLogin(bodyRequest: LoginDTO, callback: PasswordCallback<LoginResponse>) {
        callback.run {
            apiRequesting(true)
            accountServices.login(bodyRequest).enqueue(object : Callback<BaseResponse<LoginResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<LoginResponse>>,
                    response: Response<BaseResponse<LoginResponse>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        when (response.code()) {
                            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                                invalidLoginInfo()
                            }

                            else -> {
                                showMessage(getErrMessage(response))
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<LoginResponse>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }

    interface PasswordCallback<T> : BaseRepoCallback<T> {
        fun invalidLoginInfo()
    }

    /**
     * Get account info
     */
    fun getAccountInfo(callback: BaseRepoCallback<AccountInfoResponse>) {
        callback.run {
            apiRequesting(true)
            accountServices.getAccountInfo().enqueue(object : Callback<BaseResponse<AccountInfoResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<AccountInfoResponse>>,
                    response: Response<BaseResponse<AccountInfoResponse>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        showMessage(getBodyResponse(response)?.message)
                    }
                }

                override fun onFailure(call: Call<BaseResponse<AccountInfoResponse>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }

    /**
     * Request OTP code for phone verification
     */
    fun requestOtp() {

    }

    /**
     * Confirm OTP code for phone verification
     */
    fun confirmOtp() {

    }

    /**
     * Update account info
     */
    fun updateAccountInfo(

    ) {

    }

    /**
     * Update driver avatar
     */
    fun updateDriverAvatar(

    ) {

    }

    /**
     * Get CSO Phone number
     */
    fun getCsoPhoneNumber(

    ) {

    }

}
