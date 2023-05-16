package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
) : Parcelable
