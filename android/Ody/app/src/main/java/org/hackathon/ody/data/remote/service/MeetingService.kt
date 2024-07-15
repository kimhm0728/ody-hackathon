package org.hackathon.ody.data.remote.service

import org.hackathon.ody.data.remote.reqeust.MeetingInfoRequest
import org.hackathon.ody.data.remote.response.MeetingRoomResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MeetingService {

    @POST("${MEETING_RELATIVE_URL}/")
    suspend fun postMeetingRoom(
        @Body body: MeetingInfoRequest
    ): Response<MeetingRoomResponse>

    companion object {
        private const val MEETING_RELATIVE_URL = ""
    }
}