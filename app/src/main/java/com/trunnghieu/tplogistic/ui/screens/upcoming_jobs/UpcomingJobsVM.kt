package com.trunnghieu.tplogistic.ui.screens.upcoming_jobs

import androidx.lifecycle.MutableLiveData
import com.trunnghieu.tplogistic.data.preferences.AppPrefs
import com.trunnghieu.tplogistic.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.DeliveryWorkFlowRepo
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.ui.base.BaseRepoViewModel
import com.trunnghieu.tplogistic.utils.helper.AppPreferences

class UpcomingJobsVM : BaseRepoViewModel<DeliveryWorkFlowRepo, UpcomingJobsUV>() {

    override fun createRepo(): DeliveryWorkFlowRepo {
        return DeliveryWorkFlowRepo()
    }

    private val appPrefs = AppPreferences.get()

    val showRefreshButton = MutableLiveData(false)
    val jobSelectionAllowed = MutableLiveData(
        AppPreferences.get().getBoolean(AppPrefs.LOGIN.JOB_SELECTION_ALLOWED)
    )
    val enableStartWork = MutableLiveData(false)
    var selectedJob: Job? = null

    /**
     * Back to previous screen
     */
    fun backPress() {
        uiCallback?.onFragmentBackPressed()
    }

    /**
     * List assigned job
     */
    fun listAssignedJob() {
        showLoading(true)
        var jobs: List<Job> = listOf()
        repo?.getUpcomingJob(appPrefs.getString(AppPrefs.DRIVER.ID), object : BaseRepoCallback<List<Job>> {
            override fun apiResponse(data: List<Job>) {
                showLoading(false)
                jobs = data
                uiCallback?.gotAssignedJobs(jobs, true)
            }
        })
    }

    /**
     * Start work with selected job
     */
    fun startWork() {
        if (selectedJob == null) return

        selectedJob!!.showDetail = true
        if (jobSelectionAllowed.value == false) {
            // Driver cannot select the job they want
            uiCallback?.goToNextJob(selectedJob!!)
            return
        }
    }
}
