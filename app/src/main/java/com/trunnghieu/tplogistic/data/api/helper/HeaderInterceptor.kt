package com.trunnghieu.tplogistic.data.api.helper

import com.trunnghieu.tplogistic.data.preferences.AppPrefs
import com.trunnghieu.tplogistic.utils.helper.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    private val appPrefs = AppPreferences.get()

    companion object {
        const val AUTH_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBuilder = request.newBuilder()
//        requestBuilder.header(AUTH_HEADER, "Bearer ${appPrefs.getString(AppPrefs.LOGIN.ACCESS_TOKEN)}")
        requestBuilder.header(AUTH_HEADER, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2NGI0YWQwZGZjNWFmM2Y4M2Q1ZjVkYmMiLCJzY29wZXMiOlsiYWRtaW4iXSwiZXhwIjoxNjg5NTcwMTc2fQ.6RmJ_TD_EXDSlgEeNu3pSN56CWJHoX2MKN94DKQCNIM")
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}
