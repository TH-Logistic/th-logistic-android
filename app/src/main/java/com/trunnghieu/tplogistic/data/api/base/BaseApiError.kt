package com.trunnghieu.tplogistic.data.api.base

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class Error(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("error")
    val data: Any,
    @SerializedName("message")
    val message: String,
)

abstract class BaseApiError {

    companion object {
        fun <T> getErrMessage(apiResponse: Response<T>): String {
            StringBuilder().apply {
                if (!apiResponse.isSuccessful) {
                    val errorBody = apiResponse.errorBody()
                    errorBody?.let { body ->
                        val responseString = body.string()
                        if (responseString.startsWith("{") || responseString.startsWith("[")) {
                            try {
                                val error = Gson().fromJson(responseString, Error::class.java)
                                append(error.message)
                            } catch (e: Exception) {
                                append(e.message)
                            }
                        } else {
                            append(responseString)
                        }
                    }
                }
                return toString()
            }
        }

    }

}
