package com.trunnghieu.tplogistic.data.repository.remote

import com.google.gson.annotations.SerializedName
import com.trunnghieu.tplogistic.data.preferences.AppPrefs
import com.trunnghieu.tplogistic.utils.helper.AppPreferences
import com.trunnghieu.tplogistic.utils.helper.DeviceHelper

open class BaseDTO {
    @SerializedName("deviceId")
    var deviceId: String = DeviceHelper.getDeviceId()

    @SerializedName("loginId")
    var loginId: String = AppPreferences.get().getString(AppPrefs.LOGIN.PHONE_NUMBER, "")
}
