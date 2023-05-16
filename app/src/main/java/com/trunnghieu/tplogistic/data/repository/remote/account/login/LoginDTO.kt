package com.trunnghieu.tplogistic.data.repository.remote.account.login

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String,
)
