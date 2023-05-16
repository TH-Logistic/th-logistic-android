package com.trunnghieu.tplogistic.ui.screens.job_detail.delivery_location

import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface DeliveryLocationUV : BaseUserView {
    fun updateLatestJob(latestJob: Job)
    fun showDischargeMaterial(jobStatus: ApiConst.JobStatus)
    fun confirmArriveAtDelivery()
    fun deliveryArriveDone(jobStatus: ApiConst.JobStatus)
}
