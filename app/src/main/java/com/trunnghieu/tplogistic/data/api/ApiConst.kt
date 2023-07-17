package com.trunnghieu.tplogistic.data.api

import com.trunnghieu.tplogistic.utils.constants.Const

object ApiConst {

    // Url
    private const val API_VERSION = 1
    private const val BASE_DEV_URL = "http://54.174.158.64"
    private const val BASE_PROD_URL = "http://54.174.158.64"

    val BASE_API_URL = (
            if (Const.DEBUG_MODE)
                BASE_DEV_URL
            else
                BASE_PROD_URL
            )
    val BASE_WEB_URL = (
            if (Const.DEBUG_MODE)
                BASE_DEV_URL
            else
                BASE_PROD_URL
            ) + "mobile/"

    // Notification
    const val PUSH_NOTIFICATION_TYPE = "2" // 1: iOS - 2: Android

    // Job Status
    enum class JobStatus(code: Int) {
        OPEN(1),
        ASSIGNED(2),
        DRIVER_JOB_STARTED(3),
        DRIVER_PICKUP_ARRIVED(4),
        DRIVER_PICKUP_DONE(5),
        DRIVER_DELIVERY_ARRIVED(6),
        DRIVER_DISCHARGED_DONE(7),
        DRIVER_JOB_COMPLETED(8),
        CUSTOMER_CANCELLED(9);

        val statusCode: Int

        companion object {
            fun fromInt(value: Int): JobStatus {
                values().forEach {
                    if (it.statusCode == value) {
                        return it
                    }
                }
                //TODO: Update appropriate status
                return DRIVER_JOB_STARTED
            }
        }

        init {
            statusCode = code
        }
    }

    // Product
    enum class ProductType(code: Int) {
        DANGEROUS(1),
        FRAGILE(2),
        MACHINE(3),
        ELECTRONIC(4),
        AGRICULTURAL(5),
        FOOD(6),
        COSMETIC(7),
        MEDICINE(8),
        OTHERS(9);

        val productCode: Int

        companion object {
            fun fromInt(value: Int): ProductType {
                values().forEach {
                    if (it.productCode == value) {
                        return it
                    }
                }
                return OTHERS
            }
        }

        init {
            productCode = code
        }
    }

    // For TON BASED
    const val TON_BASED_NET_WEIGHT = 17500.0

    // For E-Signature
    object ESign {
        const val FOR_PICKUP = "Pickup"
        const val FOR_DELIVERY = "Unload"
    }

    // Dispatcher - Receiver
    object JobType {
        const val JOB_DISPATCHER = "dispatcher"
        const val JOB_RECEIVER = "receiver"
    }

    object Error {
        const val MESSAGE_UNPAIRED_TRUCK = "There is no vehicle or vehicle In-Active"
        const val MSG_LOGIN_ON_ANOTHER_DEVICE = "Current device ID does not match given device ID"
    }
}
