package com.trunnghieu.tplogistic.ui.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.trunnghieu.tplogistic.TPLogisticsApp
import com.trunnghieu.tplogistic.ui.screens.intro.IntroActivity
import com.trunnghieu.tplogistic.utils.CustomLogger
import com.trunnghieu.tplogistic.utils.NotificationUtils
import com.trunnghieu.tplogistic.utils.constants.TPLogisticsConst
import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.local.driver.DriverRepo
import com.trunnghieu.tplogistic.data.repository.remote.common.CommonRepo
import com.trunnghieu.tplogistic.data.repository.remote.common.driver_location_update.UpdateDriverLocationDTO
import kotlinx.coroutines.*

class DriverLocationService : Service() {

    companion object {
        var lastKnownLocationOfDriver: Location? = null
    }

    private lateinit var context: Context

    // Location
    private var isLocationRequested = false
    private var driverLocation: Location? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    // Tracking job
    private var trackingLocationJob: Job? = null

    // Repo
    private val commonRepo = CommonRepo()

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

//    override fun getLocationConfiguration(): LocationConfiguration {
//        return LocationHelper.get().createConfigUsingGoogleService()
//    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setInterval(TPLogisticsConst.LOCATION.REQUEST_GOOGLE_LOCATION_SERVICE_INTERVAL)

        if (!isLocationRequested) {
            isLocationRequested = true
            requestLocationUpdate()
//            getLocation()
        }

        trackingLocation()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTrackingLocation()
    }

    private fun trackingLocation() {
        trackingLocationJob = CoroutineScope(Dispatchers.Main).launch {
            sendDriverLocation()
            withContext(Dispatchers.IO) {
                delay(TPLogisticsConst.LOCATION.TRACKING_LOCATION_PERIOD)
                trackingLocation()
            }
        }
        trackingLocationJob!!.start()
    }

    private fun stopTrackingLocation() {
        CustomLogger.e("Stop tracking location")
        lastKnownLocationOfDriver = null

        fusedLocationClient.removeLocationUpdates(locationCallback)

        trackingLocationJob?.cancel()
        trackingLocationJob = null

        // Also stop foreground notification
        stopForeground(true)

        stopSelf()
    }

    @SuppressLint("MissingPermission")
    private fun sendDriverLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { lastLocation ->
            CustomLogger.e("Get last location: $lastLocation")
            driverLocation = lastLocation

            validateLocation {
                val latitude = driverLocation?.latitude
                val longitude = driverLocation?.longitude
                CustomLogger.e("Driver's location >>> $latitude, $longitude")

                val bodyRequest = UpdateDriverLocationDTO(
                    latitude = latitude,
                    longitude = longitude
                )
//                commonRepo.updateDriverLocation(
//                    bodyRequest,
//                    object : BaseRepoCallback<ResponseBody> {
//                        override fun apiResponse(data: LoginResponse?) {}
//
//                        override fun showMessage(message: String?) {}
//                    })
            }
        }

//        validateLocation {
//            val latitude = driverLocation?.latitude
//            val longitude = driverLocation?.longitude
//            CustomLogger.e("Driver's location >>> $latitude, $longitude")
//
//            val bodyRequest = UpdateDriverLocationDTO(
//                latitude = latitude,
//                longitude = longitude
//            )
//            commonRepo.updateDriverLocation(
//                bodyRequest,
//                object : BaseRepoCallback<ResponseBody> {
//                    override fun apiResponse(data: ResponseBody) {}
//
//                    override fun showMessage(message: String?) {}
//                })
//        }
    }

    private fun validateLocation(onDone: () -> Unit) {
        var logMessage: String
        if (driverLocation == null) {
            val latestJobStatus = DriverRepo.get().latestJobStatus.value

            logMessage = "latestJobStatus: $latestJobStatus - driverLocation == null" +
                    "\n" + "-> Consider to use lastKnownLocationOfDriver"

            if (latestJobStatus == ApiConst.JobStatus.DRIVER_PICKUP_ARRIVED
                || latestJobStatus == ApiConst.JobStatus.DRIVER_DELIVERY_ARRIVED
            ) {
                logMessage = ">>> Use lastKnownLocationOfDriver"
                driverLocation = lastKnownLocationOfDriver
            }

            CustomLogger.e(logMessage)
        }

        onDone()
    }

    private fun requestLocationUpdate() {
//        locationManager.cancel()
//        getLocation()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            val location = result.lastLocation
            CustomLogger.e("Location update callback -->> $location")
            driverLocation = location
        }

        override fun onLocationAvailability(availability: LocationAvailability) {
            super.onLocationAvailability(availability)
            val isLocationAvailable = availability.isLocationAvailable
            if (!isLocationAvailable) {
                val logMessage = "onLocationFailed: isLocationAvailable = $availability"
                CustomLogger.e(logMessage)
            }
        }
    }
}
