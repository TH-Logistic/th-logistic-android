package com.trunnghieu.tplogistic.ui.screens.job

import com.trunnghieu.tplogistic.data.repository.local.menu.HamburgerMenu
import com.trunnghieu.tplogistic.data.repository.remote.account.cso.CsoPhoneNumber
import com.trunnghieu.tplogistic.ui.base.BaseUserView


interface JobUV : BaseUserView {
    fun showMenu(show: Boolean)
    fun loadHamburgerMenu(menuList: List<HamburgerMenu>)
    fun updateLanguage()
    fun goToJobHistory()
    fun goToAccountSettings()
    fun goToLogin()
    fun gotCsoPhoneNumber(csoPhoneNumbers: List<CsoPhoneNumber>)
    fun backToVehiclePairing()
    fun goToStartWork()
    fun goToJobSummary()
}
