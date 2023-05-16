package com.trunnghieu.tplogistic.ui.screens.job_detail.discharge_material

import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface DischargeMaterialUV : BaseUserView {
    fun jobIsNotAvailable()

    fun showDoReminder(isTripBased: Boolean, hasPickupDoReminder: Boolean)

    fun showWeight(
        showWeightData: Boolean,
        showInputNetWeight: Boolean,
        showNetWeightHighlighted: Boolean,
        showEditNetWeight: Boolean
    )

    fun showLadenAndNetWeightFromPickup(ladenWeightFromPickup: Double, netWeightFromPickup: Double)

    fun showBtnAction(
        showSubmitBtn: Boolean,
        showActionBtn: Boolean,
        showDischargeBtn: Boolean,
        actionForJobComplete: Boolean
    )

    fun confirmNetWeight()
    fun resetWeight()
    fun showConfirmJob(isTripBased: Boolean, doReminder: Boolean, eSignAvailable: Boolean)
    fun startScanDO()
    fun updateLatestJob(latestJob: Job)
    fun dischargeDone(jobStatus: ApiConst.JobStatus)
    fun showESignAtDischarge()
    fun showWorkingTimeAtDischarge()
}
