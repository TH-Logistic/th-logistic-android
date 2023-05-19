package com.trunnghieu.tplogistic.data.api.services

import com.trunnghieu.tplogistic.data.repository.remote.BaseResponse
import com.trunnghieu.tplogistic.data.repository.remote.account.info.AccountInfoResponse
import com.trunnghieu.tplogistic.data.repository.remote.account.update_account.UpdateAccountInfoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserServices {

    @PATCH("users/{id}")
    fun updateInfo(
        @Path("id") driverId: String,
        @Body body: UpdateAccountInfoDto
    ): Call<BaseResponse<AccountInfoResponse>>

}
