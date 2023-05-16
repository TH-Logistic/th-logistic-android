package com.trunnghieu.tplogistic.ui.screens.healthcheck

import androidx.lifecycle.ViewModelProvider
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.databinding.FragmentHealthcheckBinding
import com.trunnghieu.tplogistic.ui.base.fragment.BaseFragment
import com.trunnghieu.tplogistic.ui.screens.job.JobVM
import com.trunnghieu.tplogistic.ui.screens.next_job.NextJobFragment

class HealthcheckFragment : BaseFragment<FragmentHealthcheckBinding, HealthcheckVM>(), HealthcheckUV {

    override fun layoutRes() = R.layout.fragment_healthcheck

    override fun viewModelClass() = HealthcheckVM::class.java

    private lateinit var jobVM: JobVM

    override fun initViewModel(viewModel: HealthcheckVM) {
        viewModel.run {
            init(this@HealthcheckFragment)
            binding.vm = this
        }
        jobVM = ViewModelProvider(requireActivity())[JobVM::class.java]
    }


    override fun initView() {

    }

    override fun initData() {

    }

    override fun initAction() {
        viewModel.jobId = jobVM.latestJob.value?.id ?: ""
    }

    override fun startWork() {
        navigator.goTo(NextJobFragment.newInstance(jobVM.latestJob.value))
    }
}
