package com.trunnghieu.tplogistic.data.api.services

import com.trunnghieu.tplogistic.data.repository.remote.BaseResponse
import com.trunnghieu.tplogistic.data.repository.remote.account.info.AccountInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserServices {

    @GET("users/{id}")
    fun getAccountInfo(
        @Path("id") driverId: String
    ): Call<BaseResponse<AccountInfoResponse>>

}
