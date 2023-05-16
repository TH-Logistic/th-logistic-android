package com.trunnghieu.tplogistic.ui.screens.job_detail.maps

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.viewmodel.BaseUiViewModel

class LocationVM : BaseUiViewModel<LocationUV>() {

    // Maps
    private var locationManager: LocationManager? = null
    private var userLatLng = LatLng(0.0, 0.0)

    // Locations
    private var isPickupLocation = true
    private var pickupLatLng: LatLng? = null
    private var dischargeLatLng: LatLng? = null

    /**
     * Init location manager
     */
    fun initLocation(locationManager: LocationManager) {
        this.locationManager = locationManager
    }

    /**
     * Location has changed
     */
    fun onLocationChanged(location: Location) {
        userLatLng = LatLng(location.latitude, location.longitude)
    }

    /**
     * Show Google Maps with data
     */
    fun showDataOnMap(job: Job? = null) {
        job ?: return
        // Pickup
        val pickupName = job.getPickUpLocation()
        pickupLatLng = LatLng(job.getPickUpLatitude(), job.getPickUpLongitude())

        // Discharge
        val dischargeName = job.getUnloadLocation()
        dischargeLatLng = LatLng(job.getUnloadLatitude(), job.getUnloadLongitude())

        uiCallback?.showDataOnMap(
            userLatLng,
            pickupName,
            pickupLatLng!!,
            dischargeName,
            dischargeLatLng!!
        )
    }

    /**
     * Move map to my location
     */
    @SuppressLint("MissingPermission")
    fun showMyLocation() {
        locationManager ?: return

        val lastKnownLocation: Location? = getLastKnownLocation()
        lastKnownLocation ?: return

        uiCallback?.zoomCameraToLastKnownLocation(
            LatLng(
                lastKnownLocation.latitude,
                lastKnownLocation.longitude
            )
        )
    }

    /**
     * Open Google Maps & start navigation
     */
    @SuppressLint("MissingPermission")
    fun openAndStartNavigationOnMap() {
        locationManager ?: return

        val lastKnownLocation: Location? = getLastKnownLocation()
        lastKnownLocation ?: return

        val uriBuilder = Uri.Builder()
            .apply {
                scheme("https")
                authority("www.google.com")
                appendPath("maps")
                appendPath("dir")
                appendPath("")
                appendQueryParameter("api", "1")
                appendQueryParameter(
                    "origin",
                    "${lastKnownLocation.latitude},${lastKnownLocation.longitude}"
                )
            }
        if (isPickupLocation) {
            uriBuilder.appendQueryParameter(
                "destination",
                "${pickupLatLng?.latitude},${pickupLatLng?.longitude}"
            )
        } else {
            uriBuilder.appendQueryParameter(
                "destination",
                "${dischargeLatLng?.latitude},${dischargeLatLng?.longitude}"
            )
        }
        val mapUrl = uriBuilder.toString()
        uiCallback?.openMap(mapUrl)
    }

    /**
    * Get last know location of the device
    */
    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {
        locationManager ?: return null

        var lastKnownLocation: Location? = null

        val providers: List<String> = locationManager!!.getProviders(true)
        for (provider in providers) {
            val l: Location = locationManager!!.getLastKnownLocation(provider) ?: continue
            if (lastKnownLocation == null || l.accuracy < lastKnownLocation.accuracy) {
                lastKnownLocation = l
            }
        }
        return lastKnownLocation
    }
}
