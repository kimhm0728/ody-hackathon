package org.woowacourse.controller;

import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.woowacourse.controller.dto.request.FcmSendRequest;
import org.woowacourse.controller.dto.request.MeetingRequest;
import org.woowacourse.controller.dto.response.MateResponse;
import org.woowacourse.controller.dto.response.MeetingResponse;
import org.woowacourse.domain.Coordinates;
import org.woowacourse.domain.Duration;
import org.woowacourse.domain.Mate;
import org.woowacourse.domain.Meeting;
import org.woowacourse.domain.Member;
import org.woowacourse.domain.NotificationType;
import org.woowacourse.service.FcmService;
import org.woowacourse.service.MateService;
import org.woowacourse.service.MeetingService;
import org.woowacourse.service.MemberService;
import org.woowacourse.service.RouteService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MeetingController {

    private final MeetingService meetingService;
    private final MateService mateService;
    private final MemberService memberService;
    private final FcmService fcmService;
    private final RouteService routeService;

    @PostMapping("/meetings")
    public ResponseEntity<MeetingResponse> save(
            @RequestHeader("Authorization") String fcmToken,
            @RequestBody MeetingRequest meetingRequest
    ) {
        Meeting meeting = meetingRequest.toMeeting();
        meetingService.save(meeting);

        Member member = memberService.findByToken(fcmToken);

        Mate mate = meetingRequest.toMate(meeting, member);
        mateService.save(mate);

        Coordinates origin = new Coordinates(meetingRequest.originLatitude(), meetingRequest.originLongitude());
        Coordinates target = new Coordinates(meetingRequest.targetLatitude(), meetingRequest.targetLongitude());

        Duration duration = routeService.calcualteDuration(origin, target);

        LocalTime departureTime = meeting.getTime().minusMinutes(duration.getMinutes());
        String body = String.format("%s에 나가야 해요!", departureTime);

        FcmSendRequest fcmSendRequest = new FcmSendRequest(fcmToken, "오디 알림 테스트", body);
        try {
            fcmService.sendPushNotification(fcmSendRequest.token(), NotificationType.DEPARTURE,
                    fcmSendRequest.title(), fcmSendRequest.body());
        } catch (Exception e) {
            log.error("Error sending notification: " + e.getMessage());
        }

        return ResponseEntity.ok(MeetingResponse.toResponse(meeting, List.of(MateResponse.toResponse(mate))));
    }
}
