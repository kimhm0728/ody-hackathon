package org.woowacourse.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.woowacourse.controller.dto.request.MeetingRequest;
import org.woowacourse.controller.dto.response.MateResponse;
import org.woowacourse.controller.dto.response.MeetingResponse;
import org.woowacourse.domain.Mate;
import org.woowacourse.domain.Meeting;
import org.woowacourse.domain.Member;
import org.woowacourse.service.MateService;
import org.woowacourse.service.MeetingService;
import org.woowacourse.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;
    private final MateService mateService;
    private final MemberService memberService;


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

        return ResponseEntity.ok(MeetingResponse.toResponse(meeting, List.of(MateResponse.toResponse(mate))));
    }
}
