package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.trunnghieu.tplogistic.utils.constants.Const
import kotlinx.parcelize.Parcelize
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
    val mustDeliverAt: Long,
    val createdAt: Long,
    val assignedAt: Long,
    val acceptedAt: Long?,
    val pickUpArriveAt: Long?,
    val pickUpDoneAt: Long?,
    val unloadArriveAt: Long?,
    val unloadDoneAt: Long?,
    val completedAt: Long?,
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(timestamp: Long?): String {
        return getFormattedTimeFromTimestamp(timestamp, Const.DATE_FORMAT)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime(timestamp: Long?): String {
        return getFormattedTimeFromTimestamp(timestamp, Const.TIME_FORMAT)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFormattedTimeFromTimestamp(timestamp: Long?, format: String): String {
        if (timestamp == null) return ""
        val instant = Instant.ofEpochMilli(timestamp)
        val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        val formatter = DateTimeFormatter.ofPattern(format)
        return formatter.format(date)
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
