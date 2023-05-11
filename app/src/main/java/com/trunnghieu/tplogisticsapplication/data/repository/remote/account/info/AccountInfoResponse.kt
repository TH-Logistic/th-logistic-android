package com.trunnghieu.tplogisticsapplication.data.repository.remote.account.info

import com.google.gson.annotations.SerializedName

data class AccountInfoResponse(
    @SerializedName("_id")
    val _id: String? = "",
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("birthday")
    val birthday: String? = "",
    @SerializedName("phoneNumber")
    val phoneNumber: String? = "",
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("deletedAt")
    val deletedAt: String? = "",
    @SerializedName("bankAccount")
    val bankAccount: String? = "",
    @SerializedName("bankName")
    val bankName: String? = "",
)
