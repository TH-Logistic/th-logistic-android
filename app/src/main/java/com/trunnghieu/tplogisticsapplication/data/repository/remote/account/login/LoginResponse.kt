package com.trunnghieu.tplogisticsapplication.data.repository.remote.account.login

import com.google.gson.annotations.SerializedName

open class LoginResponse {
    @SerializedName("token_type")
    val tokenType: String = ""

    @SerializedName("access_token")
    val accessToken: String = ""

    @SerializedName("refresh_token")
    val refreshToken: String = ""
}
