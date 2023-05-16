package com.trunnghieu.tplogistic.data.api.base

import com.trunnghieu.tplogistic.data.api.RetrofitService
import com.trunnghieu.tplogistic.data.api.services.AccountServices
import com.trunnghieu.tplogistic.data.api.services.JobServices
import com.trunnghieu.tplogistic.data.api.services.UserServices

abstract class BaseApi : BaseApiError() {
    protected val accountServices: AccountServices =
        RetrofitService.get().createService(AccountServices::class.java, "8000")
    protected val userServices: UserServices =
        RetrofitService.get().createService(UserServices::class.java, "8001")
    protected val jobServices: JobServices =
        RetrofitService.get().createService(JobServices::class.java, "8085")
}
