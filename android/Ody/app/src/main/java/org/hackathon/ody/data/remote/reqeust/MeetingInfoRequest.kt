package org.hackathon.ody.data.remote.reqeust

import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.LocalTime

data class MeetingInfoRequest(
    @Json(name = "name") val name: String,
    @Json(name = "date") val date: LocalDate,
    @Json(name = "time") val time: LocalTime,
    @Json(name = "targetAddress") val targetAddress: String,
    @Json(name = "targetLatitude") val targetLatitude: String,
    @Json(name = "targetLongitude") val targetLongitude: String,
    @Json(name = "nickname") val nickname: String,
    @Json(name = "originAddress") val originAddress: String,
    @Json(name = "originLatitude") val originLatitude: String,
    @Json(name = "originLongitude") val originLongitude: String,
)
