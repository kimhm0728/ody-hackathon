package org.woowacourse.controller.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.woowacourse.domain.Meeting;

public record MeetingResponse(
        Long id,
        String name,
        LocalDate date,
        LocalTime time,
        String targetAddress,
        String targetLatitude,
        String targetLongitude,
        int mateCount,
        MultipleResponses<MateResponse> mates,
        String inviteCode
) {

    public static MeetingResponse toResponse(Meeting meeting, List<MateResponse> mateResponses) {

        return new MeetingResponse(
                meeting.getId(),
                meeting.getName(),
                meeting.getDate(),
                meeting.getTime(),
                meeting.getTarget().getAddress(),
                meeting.getTarget().getLatitude(),
                meeting.getTarget().getLongitude(),
                mateResponses.size(),
                new MultipleResponses<>(mateResponses),
                meeting.getInviteCode()
        );
    }
}
