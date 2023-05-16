package com.trunnghieu.tplogistic.ui.screens.next_job

import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface NextJobUV : BaseUserView {
    fun onFragmentBackPressed()
    fun acceptJobSuccess(job: Job)
}
