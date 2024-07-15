package org.hackathon.ody.data.remote.reqeust

import com.squareup.moshi.Json

data class MeetingInfoRequest(
    @Json(name = "date") val date: String,
    @Json(name = "destination") val destination: Destination,
    @Json(name = "name") val name: String,
    @Json(name = "nickname") val nickname: String,
    @Json(name = "origin") val origin: Origin,
    @Json(name = "time") val time: String
) {
    data class Destination(
        @Json(name = "address") val address: String,
        @Json(name = "latitude") val latitude: String,
        @Json(name = "longitude") val longitude: String
    )

    data class Origin(
        @Json(name = "address") val address: String,
        @Json(name = "latitude") val latitude: String,
        @Json(name = "longitude") val longitude: String
    )
}