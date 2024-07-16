package org.hackathon.ody.data.remote.service

import retrofit2.http.POST

interface DeviceTokenService {
    @POST("${DEVICE_TOKEN_URL}")
    suspend fun postDeviceToken()

    companion object {
        private const val DEVICE_TOKEN_URL = "device-tokens"
    }
}
