package com.trunnghieu.tplogistic.ui.screens.job_detail.job_summary

import com.trunnghieu.tplogistic.data.api.ApiConst
import com.trunnghieu.tplogistic.data.repository.local.job.LocalJob
import com.trunnghieu.tplogistic.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.DeliveryWorkFlowRepo
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.UpdateJobStatusDto
import com.trunnghieu.tplogistic.data.repository.remote.work_flow_service.WorkFlowRepo
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel

class JobSummaryVM : BaseRepoViewModel<WorkFlowRepo, JobSummaryUV>() {

    private val driverJobRepo = DeliveryWorkFlowRepo()

    override fun createRepo(): WorkFlowRepo {
        return WorkFlowRepo()
    }

//    private val viewJobAssigned = AppPreferences.get().getBoolean(AppPrefs.LOGIN.VIEW_JOB_ASSIGNED)

    /**
     * Get job data at Job Complete
     */
    fun getJobComplete() {
//        repo?.getJobAtJobSummary(callback = object : BaseRepoCallback<Job> {
//            override fun apiRequesting(showLoading: Boolean) {
//                showLoading(showLoading)
//            }
//
//            override fun apiResponse(data: Job) {
//                uiCallback?.updateLatestJob(data)
//            }
//
//            override fun showMessage(message: String?) {
//                showError(message)
//            }
//        })
    }

    /**
     * Request to get available job
     */
    fun continueWork() {
//        driverJobRepo.getAvailableJob(object : BaseRepoCallback<AvailableJob> {
//            override fun apiRequesting(showLoading: Boolean) {
//                showLoading(showLoading)
//            }
//
//            override fun apiResponse(data: AvailableJob) {
//                if (!data.jobAvailable) {
                    noJobAvailable()
//                } else {
//                    if (!viewJobAssigned) {
//                        // viewJobAssigned = false -> Keep do the current flow
//                        RequestJobDTO.get().clear()
//                        val newJob = data.job
//                        newJob.showDetail = true
//                        // Save to local data
//                        LocalJob.get().saveLatestJob(newJob)
//                        uiCallback?.run {
//                            updateLatestJob(newJob)
//                            goToNextJob(newJob, ApiConst.JobStatus.valueOf(newJob.jobStatus))
//                        }
//                    } else {
//                        // viewJobAssigned = true -> Do the new flow
//                        uiCallback?.goToUpcomingJobs()
//                    }
//                }
//            }
//
//            override fun showMessage(message: String?) {
//                showError(message)
//            }
//        })
    }

    private fun noJobAvailable() {
//        repo?.submitContinueWork(callback = object : BaseRepoCallback<AvailableJob> {
//            override fun apiResponse(data: AvailableJob) {
//                RequestJobDTO.get().clear()
//                if (!data.jobAvailable) {
//                    // If viewJobAssigned = false -> Show popup no job with "CANCEL" button only
//                    // Also clear local job before show popup no job
//                    if (!viewJobAssigned) {
//                        LocalJob.get().clearLatestJob()
//                    }
//                    // If viewJobAssigned = true -> Show popup no job with "WAIT - CANCEL" button
//                    // No need to clear local job
//                    // because driver can back from Upcoming Jobs to Job Summary again
                    uiCallback?.noJobIsAvailable(false)
//                }
//            }
//
//            override fun showMessage(message: String?) {
//                showError(message)
//            }
//        })
    }

    /**
     * End work
     */
    fun endWork() {
//        LocalJob.get().clearLatestJob()
//        driverJobRepo.endWork(object : BaseRepoCallback<ResponseBody> {
//            override fun apiRequesting(showLoading: Boolean) {
//                showLoading(showLoading)
//            }
//
//            override fun apiResponse(data: ResponseBody) {
//                RequestJobDTO.get().clear()

        driverJobRepo.updateJobStatus(
            LocalJob.get().getLatestJob()?.id ?: "", updateJobStatusDto = UpdateJobStatusDto(
            jobStatus = ApiConst.JobStatus.DRIVER_JOB_COMPLETED.statusCode
        ), object : BaseRepoCallback<Boolean> {
            override fun apiResponse(data: Boolean) {
                showLoading(false)
                uiCallback?.endWork()
            }
        })
//            }
//
//            override fun showMessage(message: String?) {
//                showError(message)
//            }
//        })
    }
}
