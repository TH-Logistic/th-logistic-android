package com.trunnghieu.tplogistic.utils.helper

import com.trunnghieu.tplogistic.data.api.ApiConst

object JobStatusHelper {
    fun getJobStatusName(productType: ApiConst.ProductType): String {
        return when(productType) {
            ApiConst.ProductType.DANGEROUS -> "Dangerous"
            ApiConst.ProductType.FRAGILE -> "Fragile"
            ApiConst.ProductType.MACHINE -> "Machine"
            ApiConst.ProductType.ELECTRONIC -> "Electronic"
            ApiConst.ProductType.AGRICULTURAL -> "Agricultural"
            ApiConst.ProductType.FOOD -> "Food"
            ApiConst.ProductType.COSMETIC -> "Cosmetic"
            ApiConst.ProductType.MEDICINE -> "Medicine"
            ApiConst.ProductType.OTHERS -> "Others"
        }
    }
}
