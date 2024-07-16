package org.hackathon.ody.data.remote.location.service

import org.hackathon.ody.data.remote.location.response.LocationSearchResponse
import org.hackathon.ody.data.remote.response.MeetingRoomResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoLocationService {
    @GET("v2/local/search/address.json")
    fun searchLocation(
        @Header("Authorization") key: String,
        @Query("query") query: String
    ): Call<LocationSearchResponse>
}
