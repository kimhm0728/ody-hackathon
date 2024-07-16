package org.woowacourse.controller;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.woowacourse.domain.Member;
import org.woowacourse.domain.Token;
import org.woowacourse.dto.request.TokenRequest;
import org.woowacourse.service.MemberService;
import org.woowacourse.service.TokenService;

@RestController
public class TokenController {

    private final MemberService memberService;
    private final TokenService tokenService;

    public TokenController(MemberService memberService, TokenService tokenService) {
        this.memberService = memberService;
        this.tokenService = tokenService;
    }

    @PostMapping("/device-tokens")
    public ResponseEntity<Void> save(@RequestHeader(value = HttpHeaders.AUTHORIZATION) TokenRequest tokenRequest) {
        Member member = memberService.save();
        Token token = tokenService.save(tokenRequest, member);
        return ResponseEntity.created(URI.create("/token/" + token.getId()))
                .build();
    }
}
