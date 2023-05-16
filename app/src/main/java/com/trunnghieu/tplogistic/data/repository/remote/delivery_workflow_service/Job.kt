package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    val bookingNo: String,
    var showDetail: Boolean = true,
    val loadNo: Int = 1,

    val id: String,
    val transportation: TransportationDto,
    val startingGarage: GarageDto,
    val endingGarage: GarageDto,
    val products: List<ProductDto>,
    val route: RouteDto,
    val healthcheck: HealthcheckDto,
    val isTonBased: Boolean,
    var status: Int,
    val totalPrice: Double,
    val mustDeliverAt: String,
    val createdAt: String,
    val assignedAt: String,
    val acceptedAt: String?,
    val pickUpArriveAt: String?,
    val pickUpDoneAt: String?,
    val unloadArriveAt: String?,
    val unloadDoneAt: String?,
    val completedAt: String?,
    val pickUpContactName: String,
    val pickUpContactNo: String,
    val unloadContactName: String,
    val unloadContactNo: String,
    val notesToDriver: String?,
) : Parcelable {
    fun getPickUpLocation(): String {
        return route.fromLocation.name
    }

    fun getUnloadLocation(): String {
        return route.toLocation.name
    }

    fun getProductNames(): String {
        return products.joinToString("\n") {
            it.name
        }
    }

    fun getDate(formattedInput: String?): String {
        if (formattedInput.isNullOrEmpty()) return ""
        return formattedInput.split(" ").getOrNull(1) ?: ""
    }

    fun getTime(formattedInput: String?): String {
        if (formattedInput.isNullOrEmpty()) return ""
        return formattedInput.split(" ").getOrNull(0) ?: ""
    }

    fun getPickUpLatitude(): Double {
        return route.fromLocation.latitude
    }

    fun getPickUpLongitude(): Double {
        return route.fromLocation.longitude
    }

    fun getUnloadLatitude(): Double {
        return route.toLocation.latitude
    }

    fun getUnloadLongitude(): Double {
        return route.toLocation.longitude
    }
}
