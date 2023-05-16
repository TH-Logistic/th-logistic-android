package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthcheckDto(
    val id: String,
    val note: String,
    val createdAt: String,
    val isHealthcheckOk: Boolean,
    val isTiresOk: Boolean,
    val isLightOk: Boolean,
    val isBrakeOk: Boolean,
    val isFluidLevelOk: Boolean,
    val isBatteryOk: Boolean,
    val isWiperOk: Boolean,
) : Parcelable
