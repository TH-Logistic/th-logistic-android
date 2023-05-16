package com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDto(
    val id: String,
    val name: String,
    val unit: String,
    val types: List<Int>,
    val basePrice: Double,
) : Parcelable
