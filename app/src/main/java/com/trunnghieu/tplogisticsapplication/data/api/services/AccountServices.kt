package com.trunnghieu.tplogisticsapplication.data.api.services

import com.trunnghieu.tplogisticsapplication.data.repository.remote.BaseResponse
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.info.AccountInfoResponse
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login.LoginDTO
import com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AccountServices {

    @POST("/login")
    fun login(
        @Body bodyRequest: LoginDTO
    ): Call<BaseResponse<LoginResponse>>

    @GET("users/me")
    fun getAccountInfo(): Call<BaseResponse<AccountInfoResponse>>

//    @POST("account/resetPassword")
//    fun resetPassword(
//        @Body bodyRequest: ResetPasswordDTO
//    ): Call<ResponseBody>
//
//    @POST("account/changePassword")
//    fun changePassword(
//        @Body bodyRequest: ChangePasswordDTO
//    ): Call<ResponseBody>
//
//    @POST("deliveryDriver/pairTruck")
//    fun vehiclePairing(
//        @Body bodyRequest: VehiclePairingDTO
//    ): Call<VehiclePairing>
//
//    @POST("deliveryDriver/unpairTruck")
//    fun unpairTruck(
//        @Body bodyRequest: BaseDTO
//    ): Call<UnpairTruck>
//

//
//    @POST("account/requestOTP")
//    fun requestOtp(
//        @Body bodyRequest: RequestOtpDTO
//    ): Call<RequestOtp>
//
//    @POST("account/confirmOTP")
//    fun confirmOtp(
//        @Body bodyRequest: ConfirmOtpDTO
//    ): Call<ResponseBody>
//
//    @POST("account/updateAccountInfo")
//    fun updateAccountInfo(
//        @Body bodyRequest: AccountInfoDTO
//    ): Call<ResponseBody>
//
//    @Multipart
//    @POST("account/uploadDriverAvatar")
//    fun updateDriverAvatar(
//        @Part file: MultipartBody.Part,
//        @Part("loginId") loginId: RequestBody,
//        @Part("deviceId") deviceId: RequestBody
//    ): Call<ResponseBody>
//
//    @POST("account/getSupportNumber")
//    fun getCsoPhoneNumber(
//        @Body bodyRequest: BaseDTO
//    ): Call<List<CsoPhoneNumber>>
//
//    @POST("account/getTermsConditionsPdf")
//    fun getTermsConditionsPdf(
//        @Body body: BaseDTO
//    ): Call<GetTermsConditions>
//
//    @POST("account/acceptedTermsConditions")
//    fun acceptedTermsConditions(
//        @Body body: AcceptedTermsConditionsDTO
//    ): Call<ResponseBody>
}
