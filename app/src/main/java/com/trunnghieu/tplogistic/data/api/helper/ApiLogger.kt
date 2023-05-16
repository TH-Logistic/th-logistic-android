package com.trunnghieu.tplogistic.data.api.helper

import android.util.Log
import com.trunnghieu.tplogistic.utils.CustomLogger
import okhttp3.logging.HttpLoggingInterceptor

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                CustomLogger.json(message)
            } catch (m: Exception) {
                Log.w("API exception:", m.toString())
            }
        } else {
            Log.w("API:", message)
            return
        }
    }
}
