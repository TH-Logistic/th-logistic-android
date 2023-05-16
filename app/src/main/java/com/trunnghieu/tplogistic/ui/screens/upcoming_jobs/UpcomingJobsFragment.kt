package com.trunnghieu.tplogistic.ui.screens.upcoming_jobs

import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.databinding.FragmentUpcomingJobsBinding
import com.trunnghieu.tplogistic.ui.base.fragment.BaseFragment
import com.trunnghieu.tplogistic.ui.screens.healthcheck.HealthcheckFragment
import com.trunnghieu.tplogistic.ui.screens.job.JobVM
import com.trunnghieu.tplogistic.ui.screens.upcoming_jobs.adapter.UpcomingJobsAdapter

class UpcomingJobsFragment : BaseFragment<FragmentUpcomingJobsBinding, UpcomingJobsVM>(),
    UpcomingJobsUV, UpcomingJobsAdapter.OnJobSelectedListener {

    companion object {
        // onFragmentResult
        const val KEY_REFRESH_JOBS = "KEY_REFRESH_JOBS"
    }

    override fun layoutRes() = R.layout.fragment_upcoming_jobs

    override fun viewModelClass() = UpcomingJobsVM::class.java

    private lateinit var jobVM: JobVM

    override fun initViewModel(viewModel: UpcomingJobsVM) {
        viewModel.run {
            init(this@UpcomingJobsFragment)
            binding.vm = this
        }
        jobVM = ViewModelProvider(requireActivity())[JobVM::class.java]
    }

    private val upcomingJobsAdapter = UpcomingJobsAdapter(this)

    override fun initView() {
        binding.rvJob.itemAnimator = null
        binding.rvJob.adapter = upcomingJobsAdapter
    }

    override fun initData() {
        viewModel.listAssignedJob()
    }

    override fun initAction() {

    }

    override fun onFragmentBackPressed() {
        jobVM.backToVehiclePairing()
    }

    override fun listAssignedJobsIsEmpty() {
        upcomingJobsAdapter.apply {
            submitList(null)
            resetLastCheckedItemPosition()
        }
    }

    override fun gotAssignedJobs(jobList: List<Job>, jobSelectionAllowed: Boolean) {
        upcomingJobsAdapter.apply {
            submitList(null)
            submitList(jobList)
            setJobSelectionAllowed(jobSelectionAllowed)
            resetLastCheckedItemPosition()
        }
    }

    override fun goToNextJob(selectedJob: Job) {
        jobVM.showHighlightDataChanged(show = false, disableImmediately = true)
        setFragmentResultListener(KEY_REFRESH_JOBS) { requestKey, _ ->
            if (requestKey == KEY_REFRESH_JOBS) {
                refreshAssignedJobs()
            }
        }
        jobVM.latestJob.value = selectedJob
        navigator.goTo(HealthcheckFragment())
    }

    override fun selectedJob(job: Job?) {
        viewModel.apply {
            selectedJob = job
            enableStartWork.value = job != null
        }
    }

    fun getViewModel() = viewModel

    fun refreshAssignedJobs() {
        viewModel.apply {
            enableStartWork.value = viewModel.jobSelectionAllowed.value == false
            listAssignedJob()
        }
    }
}
