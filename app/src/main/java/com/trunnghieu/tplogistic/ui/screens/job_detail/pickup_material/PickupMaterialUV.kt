package com.trunnghieu.tplogistic.ui.screens.job_detail.pickup_material

import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface PickupMaterialUV : BaseUserView {
    fun jobIsNotAvailable()

    fun showDoReminder(isTripBased: Boolean, hasPickupDoReminder: Boolean, pickUpQRScanned: Boolean)

    fun showWeight(
        showWeightData: Boolean,
        showInputNetWeight: Boolean,
        showNetWeightHighlighted: Boolean,
        showEditNetWeight: Boolean
    )

    fun showBtnAction(showActionBtn: Boolean, actionForPickupDone: Boolean)

    fun resetWeight()
    fun confirmPickupDone(
        isTripBased: Boolean,
        doReminder: Boolean,
        eSignAvailable: Boolean,
        didTonnageSubmissionLocationIsPickup: Boolean
    )

    fun confirmNetWeight()
    fun updateLatestJob(latestJob: Job)
    fun showESignAtPickup()
    fun showWorkingScreen()
    fun pickupDone(jobStatus: ApiConst.JobStatus)
}
