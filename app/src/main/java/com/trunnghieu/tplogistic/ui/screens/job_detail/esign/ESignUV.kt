package com.trunnghieu.tplogistic.ui.screens.job_detail.esign

import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface ESignUV : BaseUserView {
    fun clearSignature()
    fun showProgress(show: Boolean)
    fun progressUpdate(progress: Int)
    fun updateLatestJob(latestJob: Job)
    fun uploadESignSuccess()
    fun cancelESign()
    fun enableConfirm(enable: Boolean)
}
