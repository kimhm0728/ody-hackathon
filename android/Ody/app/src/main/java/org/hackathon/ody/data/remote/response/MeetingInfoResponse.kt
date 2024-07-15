package org.hackathon.ody.data.remote.response

import com.squareup.moshi.Json

data class MeetingInfoResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "date") val date: String,
    @Json(name = "time") val time: String,
    @Json(name = "destination") val destination: Destination,
    @Json(name = "mateCount") val mateCount: Int,
    @Json(name = "mates") val mates: List<Mate>,
    @Json(name = "invitedCode") val invitedCode: String
) {
    data class Destination(
        @Json(name = "address") val address: String,
        @Json(name = "latitude") val latitude: String,
        @Json(name = "longitude") val longitude: String
    )

    data class Mate(
        @Json(name = "nickname") val nickname: String
    )
}
