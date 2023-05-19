package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import com.trunnghieu.tplogistic.data.repository.remote.BaseRepo
import com.trunnghieu.tplogistic.data.repository.remote.BaseRepoCallback
import com.trunnghieu.tplogistic.data.repository.remote.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class DeliveryWorkFlowRepo : BaseRepo() {
    fun getUpcomingJob(driverId: String, callback: BaseRepoCallback<List<Job>>) {
        callback.run {
            apiRequesting(true)
            jobServices.getUpcomingJobs(driverId).enqueue(object : Callback<BaseResponse<List<Job>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<Job>>>,
                    response: Response<BaseResponse<List<Job>>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        showMessage(getErrMessage(response))
                    }
                }

                override fun onFailure(call: Call<BaseResponse<List<Job>>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }

    fun getJobById(jobId: String, callback: BaseRepoCallback<Job>) {
        callback.run {
            apiRequesting(true)
            jobServices.getJobById(jobId).enqueue(object : Callback<BaseResponse<Job>> {
                override fun onResponse(
                    call: Call<BaseResponse<Job>>,
                    response: Response<BaseResponse<Job>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        showMessage(getErrMessage(response))
                    }
                }

                override fun onFailure(call: Call<BaseResponse<Job>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }

    fun submitHealthcheck(jobId: String, updateJobStatusDto: UpdateJobStatusDto, callback: BaseRepoCallback<Boolean>) {
        callback.run {
            apiRequesting(true)
            jobServices.updateJobStatus(jobId, updateJobStatusDto).enqueue(object : Callback<BaseResponse<Boolean>> {
                override fun onResponse(
                    call: Call<BaseResponse<Boolean>>,
                    response: Response<BaseResponse<Boolean>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        showMessage(getErrMessage(response))
                    }
                }

                override fun onFailure(call: Call<BaseResponse<Boolean>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }

    fun updateJobStatus(jobId: String, updateJobStatusDto: UpdateJobStatusDto, callback: BaseRepoCallback<Boolean>) {
        callback.run {
            apiRequesting(true)
            jobServices.updateJobStatus(jobId, updateJobStatusDto).enqueue(object : Callback<BaseResponse<Boolean>> {
                override fun onResponse(
                    call: Call<BaseResponse<Boolean>>,
                    response: Response<BaseResponse<Boolean>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        showMessage(getErrMessage(response))
                    }
                }

                override fun onFailure(call: Call<BaseResponse<Boolean>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }

    fun getHistoryJobs(driverId: String, date: String, callback: BaseRepoCallback<List<Job>>) {
        callback.run {
            apiRequesting(true)
            jobServices.getHistoryJobs(driverId, date).enqueue(object : Callback<BaseResponse<List<Job>>> {
                override fun onResponse(
                    call: Call<BaseResponse<List<Job>>>,
                    response: Response<BaseResponse<List<Job>>>
                ) {
                    apiRequesting(false)
                    if (response.isSuccessful) {
                        getBodyResponse(response)?.let {
                            it.data?.let { data -> apiResponse(data) }
                        }
                    } else {
                        showMessage(getErrMessage(response))
                    }
                }

                override fun onFailure(call: Call<BaseResponse<List<Job>>>, t: Throwable) {
                    apiRequesting(false)
                    if (t is UnknownHostException) {
                        callback.connectionError()
                        return
                    }
                    showMessage(t.message)
                }
            })
        }
    }
}
