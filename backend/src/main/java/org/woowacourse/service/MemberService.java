package org.woowacourse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woowacourse.domain.Member;
import org.woowacourse.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save() {
        Member member = Member.builder().build();
        return memberRepository.save(member);
    }

    public Member findByToken(String token) {
        return memberRepository.findByToken(token);
    }
}
