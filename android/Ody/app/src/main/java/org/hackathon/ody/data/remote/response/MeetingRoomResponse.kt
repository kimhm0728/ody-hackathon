package org.hackathon.ody.data.remote.response

import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.LocalTime

data class MeetingRoomResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "date") val date: LocalDate,
    @Json(name = "time") val time: LocalTime,
    @Json(name = "targetAddress") val address: String,
    @Json(name = "targetLatitude") val latitude: String,
    @Json(name = "targetLongitude") val longitude: String,
    @Json(name = "mateCount") val mateCount: Long,
    @Json(name = "mates") val mates: Mates,
    @Json(name = "inviteCode") val inviteCode: String,
) {
    data class Mates(
        @Json(name = "mate") val mate: List<Mate>
    )

    data class Mate(
        @Json(name = "nickname") val nickname: String,
    )
}
