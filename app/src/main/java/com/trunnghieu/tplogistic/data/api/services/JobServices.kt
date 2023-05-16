package com.trunnghieu.tplogistic.data.api.services

import com.trunnghieu.tplogistic.data.repository.remote.BaseResponse
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.UpdateJobStatusDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface JobServices {
    @GET("/api/v1/job/upcoming-jobs/{id}")
    fun getUpcomingJobs(
        @Path("id") driverId: String
    ): Call<BaseResponse<List<Job>>>

    @GET("/api/v1/job/{id}")
    fun getJobById(
        @Path("id") jobId: String
    ): Call<BaseResponse<Job>>

    @PUT("api/v1/job/job-status/{id}")
    fun updateJobStatus(
        @Path("id") jobId: String,
        @Body bodyRequest: UpdateJobStatusDto
    ): Call<BaseResponse<Boolean>>
}
