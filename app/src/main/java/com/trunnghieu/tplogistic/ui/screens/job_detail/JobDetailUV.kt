package com.trunnghieu.tplogistic.ui.screens.job_detail

import androidx.fragment.app.Fragment
import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface JobDetailUV : BaseUserView {
    fun showDeliveryLocation()
    fun showDischargeMaterial(showJobCompleteButton: Boolean)
    fun replaceRootFragment(fragment: Fragment)
}
