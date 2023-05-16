package com.trunnghieu.tplogistic.ui.screens.job_detail.pickup_location

import android.location.Location
import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.local.job.LocalJob
import com.trunnghieu.tplogistic.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.DeliveryWorkFlowRepo
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.UpdateJobStatusDto
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel
import com.trunnghieu.tplogistic.ui.services.DriverLocationService
import com.trunnghieu.tplogistic.utils.CustomLogger

class PickupLocationVM : BaseRepoViewModel<DeliveryWorkFlowRepo, PickupLocationUV>() {

    override fun createRepo(): DeliveryWorkFlowRepo {
        return DeliveryWorkFlowRepo()
    }

    var isConnected = true
    private var hasSubmitPickupArrive = false
    private lateinit var latestJob: Job

    /**
     * Show local job data
     * In case no-connectivity
     */
    fun showLocalJob() {
        val latestJob = LocalJob.get().getLatestJob() ?: return
        uiCallback?.updateLatestJob(latestJob)
    }

    /**
     * Get job data at Pickup Arrive
     */
    fun getJobAtPickupArrive() {
        hasSubmitPickupArrive = false
//        repo?.getJobAtPickupArrive(callback = object : BaseRepoCallback<Job> {
//            override fun apiRequesting(showLoading: Boolean) {
//                showLoading(showLoading)
//            }
//
//            override fun apiResponse(data: Job) {
//                uiCallback?.updateLatestJob(data)
//            }
//
//            override fun showMessage(message: String?) {
//                showError(message)
//            }
//        })
    }

    /**
     * Calculate location with radius to detect if driver is nearly at Pickup Location
     */
    fun calculateLocationWithRadius(
        location: Location,
        pickupLat: Double,
        pickupLng: Double,
        radius: Double
    ) {
        DriverLocationService.lastKnownLocationOfDriver = location
        val pickupLatLng = Location("").apply {
            latitude = pickupLat
            longitude = pickupLng
        }
        val distance = location.distanceTo(pickupLatLng)
        if (distance > radius) return
        if (hasSubmitPickupArrive) return
        hasSubmitPickupArrive = true
        CustomLogger.e("Driver is nearly Pickup Location -> Submit Pickup Arrive")
        submitPickupArrive()
    }

    /**
     * Action arrive at delivery
     */
    fun confirmArriveAtPickup() {
        uiCallback?.confirmArriveAtPickup()
    }

    /**
     * Submit pickup arrive status
     */
    fun submitPickupArrive() {
//        if (!isConnected) {
        // No-Connectivity Mode
        latestJob = LocalJob.get().getLatestJob() ?: return
        latestJob.status = ApiConst.JobStatus.DRIVER_PICKUP_ARRIVED.statusCode

        showLoading(true)
        repo?.updateJobStatus(latestJob.id, updateJobStatusDto = UpdateJobStatusDto(
            jobStatus = ApiConst.JobStatus.DRIVER_PICKUP_ARRIVED.statusCode
        ), object : BaseRepoCallback<Boolean> {
            override fun apiResponse(data: Boolean) {
                showLoading(false)
                uiCallback?.pickupArriveDone(ApiConst.JobStatus.DRIVER_PICKUP_ARRIVED)
            }
        })
//            return
//        }

//        repo?.submitPickupArrive(callback = object : BaseRepoCallback<Job> {
//            override fun apiRequesting(showLoading: Boolean) {
//                showLoading(showLoading)
//            }
//
//            override fun apiResponse(data: Job) {
//                moveToPickupMaterial(data)
//            }
//
//            override fun showMessage(message: String?) {
//                showError(message)
//            }
//        })
    }

    private fun moveToPickupMaterial(latestJob: Job) {
        uiCallback?.run {
            updateLatestJob(latestJob)
            pickupArriveDone(ApiConst.JobStatus.fromInt(latestJob.status))
        }
    }
}
