package com.trunnghieu.tplogistic.ui.screens.job_detail.job_summary

import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface JobSummaryUV : BaseUserView {
    fun updateLatestJob(latestJob: Job)
    fun noJobIsAvailable(viewJobAssigned: Boolean)
    fun goToNextJob(newJob: Job, jobStatus: ApiConst.JobStatus)
    fun endWork()
    fun goToUpcomingJobs()
}
