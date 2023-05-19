package com.trunnghieu.tplogistic.data.repository.remote.account.update_account

import com.google.gson.annotations.SerializedName

data class UpdateAccountInfoDto(
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("name")
    val name: String,
)
