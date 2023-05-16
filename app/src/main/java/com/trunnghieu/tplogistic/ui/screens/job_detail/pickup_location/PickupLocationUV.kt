package com.trunnghieu.tplogistic.ui.screens.job_detail.pickup_location

import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface PickupLocationUV : BaseUserView {
    fun updateLatestJob(latestJob: Job)
    fun confirmArriveAtPickup()
    fun pickupArriveDone(jobStatus: ApiConst.JobStatus)
}
