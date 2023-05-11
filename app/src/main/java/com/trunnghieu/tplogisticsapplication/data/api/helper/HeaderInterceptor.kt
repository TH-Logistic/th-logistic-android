package com.trunnghieu.tplogisticsapplication.data.api.helper

import com.trunnghieu.tplogisticsapplication.data.api.ApiConst
import com.trunnghieu.tplogisticsapplication.data.preferences.AppPrefs
import com.trunnghieu.tplogisticsapplication.utils.helper.AppPreferences
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
        requestBuilder.header(AUTH_HEADER, "Bearer ${appPrefs.getString(AppPrefs.LOGIN.ACCESS_TOKEN)}")
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}
