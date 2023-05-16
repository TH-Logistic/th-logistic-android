package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DriverInfoDto(
    val id: String,
    val avatarUrl: String,
    val name: String,
    val phoneNumber: String,
    val dateOfBirth: String,
) : Parcelable
