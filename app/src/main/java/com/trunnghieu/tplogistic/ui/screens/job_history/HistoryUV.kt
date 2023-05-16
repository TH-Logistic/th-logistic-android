package com.trunnghieu.tplogistic.ui.screens.job_history

import com.trunnghieu.tplogistic.ui.base.BaseUserView

interface HistoryUV : BaseUserView {
    fun onFragmentBackPressed()
    fun showDatePicker()
//    fun previewAndDownloadAllEdo(histories: List<Job>)
}
