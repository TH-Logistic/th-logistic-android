package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateJobStatusDto(
    val jobStatus: Int,
    val healthcheck: HealthcheckDetailDto? = null,
) : Parcelable

@Parcelize
data class HealthcheckDetailDto(
    val jobId: String,
    val note: String,
    val isTiresOk: Boolean,
    val isLightOk: Boolean,
    val isBrakeOk: Boolean,
    val isFluidLevelOk: Boolean,
    val isBatteryOk: Boolean,
    val isWiperOk: Boolean,
) : Parcelable


