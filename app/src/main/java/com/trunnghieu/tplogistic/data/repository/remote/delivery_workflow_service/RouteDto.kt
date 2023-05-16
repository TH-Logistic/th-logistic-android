package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteDto(
    val id: String,
    val fromLocation: LocationDto,
    val toLocation: LocationDto,
    val length: Double,
    val tripBasedCost: Double,
    val tonBasedLimit: Double,
    val isEnable: Boolean,
) : Parcelable
