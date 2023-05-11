package com.trunnghieu.tplogisticsapplication.data.api.base

import com.trunnghieu.tplogisticsapplication.data.api.RetrofitService
import com.trunnghieu.tplogisticsapplication.data.api.services.AccountServices

abstract class BaseApi : BaseApiError() {
    protected val accountServices: AccountServices =
        RetrofitService.get().createService(AccountServices::class.java, "8000")
}
