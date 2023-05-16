package com.trunnghieu.tplogistic.ui.screens.upcoming_jobs

import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface UpcomingJobsUV : BaseUserView {
    fun onFragmentBackPressed()
    fun listAssignedJobsIsEmpty()
    fun gotAssignedJobs(jobList: List<Job>, jobSelectionAllowed: Boolean)
    fun goToNextJob(selectedJob: Job)
}
