package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransportationDto(
    val id: String,
    val licensePlate: String,
    val load: Double,
    val deliveryStatus: Int,
    val isInGarage: Boolean,
    val garage: GarageDto,
    val mainDriver: DriverInfoDto,
    val coDriver: DriverInfoDto,
) : Parcelable
