package org.woowacourse.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.woowacourse.controller.dto.request.DeviceTokenRequest;
import org.woowacourse.domain.DeviceToken;
import org.woowacourse.domain.Member;
import org.woowacourse.service.DeviceTokenService;
import org.woowacourse.service.MemberService;

@RestController
@RequiredArgsConstructor
public class DeviceTokenController {

    private final MemberService memberService;
    private final DeviceTokenService deviceTokenService;

    @PostMapping("/device-tokens")
    public ResponseEntity<Void> save(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) DeviceTokenRequest deviceTokenRequest) {
        Member member = memberService.save();
        DeviceToken deviceToken = deviceTokenService.save(deviceTokenRequest.value(), member);
        return ResponseEntity.created(URI.create("/token/" + deviceToken.getId()))
                .build();
    }
}
