package com.trunnghieu.tplogistic.data.repository.remote.common.driver_location_update

import com.google.gson.annotations.SerializedName
import com.trunnghieu.tplogistic.data.repository.remote.BaseDTO

data class UpdateDriverLocationDTO(
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null
) : BaseDTO()
