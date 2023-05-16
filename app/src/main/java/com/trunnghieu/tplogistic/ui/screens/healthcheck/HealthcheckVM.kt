package com.trunnghieu.tplogistic.ui.screens.healthcheck

import androidx.lifecycle.MutableLiveData
import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.DeliveryWorkFlowRepo
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.HealthcheckDetailDto
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.UpdateJobStatusDto
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel
import com.trunnghieu.tplogistic.utils.helper.AppPreferences

class HealthcheckVM : BaseRepoViewModel<DeliveryWorkFlowRepo, HealthcheckUV>() {

    override fun createRepo(): DeliveryWorkFlowRepo {
        return DeliveryWorkFlowRepo()
    }

    private val appPrefs = AppPreferences.get()

    var jobId: String = ""

    val isTiresOk = MutableLiveData(false)
    val isLightOk = MutableLiveData(false)
    val isBrakeOk = MutableLiveData(false)
    val isFluidLevelOk = MutableLiveData(false)
    val isBatteryOk = MutableLiveData(false)
    val isWiperOk = MutableLiveData(false)

    /**
     * Start work with selected job
     */
    fun startWork() {
        val updateJobStatusDto = UpdateJobStatusDto(
            jobStatus = ApiConst.JobStatus.DRIVER_JOB_STARTED.statusCode,
            healthcheck = HealthcheckDetailDto(
                jobId = jobId,
                note = "",
                isTiresOk = isTiresOk.value ?: false,
                isLightOk = isLightOk.value ?: false,
                isBrakeOk = isBrakeOk.value ?: false,
                isFluidLevelOk = isFluidLevelOk.value ?: false,
                isBatteryOk = isBatteryOk.value ?: false,
                isWiperOk = isWiperOk.value ?: false
            )
        )
        showLoading(true)
        repo?.submitHealthcheck(jobId, updateJobStatusDto, object : BaseRepoCallback<Boolean> {
            override fun apiResponse(data: Boolean) {
                showLoading(false)
                uiCallback?.startWork()
            }
        })
    }
}
