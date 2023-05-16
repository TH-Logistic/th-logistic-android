package com.trunnghieu.tplogistic.data.api.services

import com.trunnghieu.tplogistic.data.repository.remote.BaseResponse
import com.trunnghieu.tplogistic.data.repository.remote.account.info.AccountInfoResponse
import com.trunnghieu.tplogistic.data.repository.remote.account.login.LoginDTO
import com.trunnghieu.tplogistic.data.repository.remote.account.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountServices {

    @POST("/login")
    fun login(
        @Body bodyRequest: LoginDTO
    ): Call<BaseResponse<LoginResponse>>

    @GET("/users/me")
    fun accountInfo(): Call<BaseResponse<AccountInfoResponse>>
}
