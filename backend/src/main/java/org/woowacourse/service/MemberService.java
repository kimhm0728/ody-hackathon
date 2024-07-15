package org.woowacourse.service;

import org.springframework.stereotype.Service;
import org.woowacourse.domain.Member;
import org.woowacourse.repository.MemberRepository;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save() {
        Member member = new Member();
        return memberRepository.save(member);
    }
}
