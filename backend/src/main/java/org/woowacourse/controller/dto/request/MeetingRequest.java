package org.woowacourse.controller.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import org.woowacourse.domain.Mate;
import org.woowacourse.domain.Meeting;
import org.woowacourse.domain.Member;
import org.woowacourse.domain.Origin;
import org.woowacourse.domain.Target;

public record MeetingRequest(
        String name,
        LocalDate date,
        LocalTime time,
        String targetAddress,
        String targetLatitude,
        String targetLongitude,
        String nickname,
        String originAddress,
        String originLatitude,
        String originLongitude
) {

    public Meeting toMeeting() {
        Target target = Target.builder()
                .address(targetAddress)
                .latitude(targetLatitude)
                .longitude(targetLongitude)
                .build();

        return Meeting.builder()
                .name(name)
                .date(date)
                .time(time)
                .target(target)
                .name(nickname)
                .inviteCode("에궁")
                .build();
    }

    public Mate toMate(Meeting meeting, Member member) {
        Origin origin = Origin.builder()
                .address(originAddress)
                .latitude(originLatitude)
                .longitude(originLongitude)
                .build();

        return Mate.builder()
                .member(member)
                .meeting(meeting)
                .nickname(nickname)
                .origin(origin)
                .build();
    }
}
