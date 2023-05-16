package com.trunnghieu.tplogistic.ui.screens.job_detail.delivery_location

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

class DeliveryLocationVM : BaseRepoViewModel<DeliveryWorkFlowRepo, DeliveryLocationUV>() {

    override fun createRepo(): DeliveryWorkFlowRepo {
        return DeliveryWorkFlowRepo()
    }

    var isConnected = true
    private var hasSubmitDeliveryArrive = false
    private val latestJob: Job = LocalJob.get().getLatestJob()!!

    /**
     * Show local job data
     * In case no-connectivity
     */
    fun showLocalJob() {
//        val latestJob = LocalJob.get().getLatestJob() ?: return
//        uiCallback?.updateLatestJob(latestJob)
//        if (!latestJob.hasDeliveryLocation) {
//            uiCallback?.showDischargeMaterial(ApiConst.JobStatus.valueOf(latestJob.jobStatus))
//        }
    }

    /**
     * Get job data at delivery arrive
     */
    fun getJobAtDeliveryArrive() {
//        hasSubmitDeliveryArrive = false
//        repo?.getJobAtDeliveryArrive(callback = object : BaseRepoCallback<Job> {
//            override fun apiRequesting(showLoading: Boolean) {
//                showLoading(showLoading)
//            }
//
//            override fun apiResponse(data: Job) {
//                uiCallback?.updateLatestJob(data)
//                if (!data.hasDeliveryLocation) {
//                    uiCallback?.showDischargeMaterial(ApiConst.JobStatus.valueOf(data.jobStatus))
//                }
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
        deliveryLat: Double,
        deliveryLng: Double,
        radius: Double
    ) {
        DriverLocationService.lastKnownLocationOfDriver = location
        val deliveryLatLng = Location("").apply {
            latitude = deliveryLat
            longitude = deliveryLng
        }
        val distance = location.distanceTo(deliveryLatLng)
        if (distance > radius) return
        if (hasSubmitDeliveryArrive) return
        hasSubmitDeliveryArrive = true
        CustomLogger.e("Driver is nearly Delivery Location -> Submit Delivery Arrive")
        submitDeliveryArrive()
    }

    /**
     * Action arrive at delivery
     */
    fun confirmArriveAtDelivery() {
        uiCallback?.confirmArriveAtDelivery()
    }

    /**
     * Submit delivery arrive status
     */
    fun submitDeliveryArrive() {
        showLoading(true)
        repo?.updateJobStatus(latestJob.id, updateJobStatusDto = UpdateJobStatusDto(
            jobStatus = ApiConst.JobStatus.DRIVER_DELIVERY_ARRIVED.statusCode
        ), object : BaseRepoCallback<Boolean> {
            override fun apiResponse(data: Boolean) {
                showLoading(false)
                uiCallback?.deliveryArriveDone(ApiConst.JobStatus.DRIVER_DELIVERY_ARRIVED)
            }
        })
    }
}
