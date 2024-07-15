package org.woowacourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.woowacourse.domain.DeviceToken;
import org.woowacourse.domain.Member;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DeviceTokenRepository deviceTokenRepository;

    @DisplayName("기기 토큰으로 회원을 찾는다.")
    @Test
    void findByToken() {
        Member member = memberRepository.save(Member.builder()
                .build());

        DeviceToken deviceToken = deviceTokenRepository.save(DeviceToken.builder()
                .token("test")
                .member(member)
                .build());

        Member findMember = memberRepository.findByToken(deviceToken.getToken());

        assertThat(member.getId()).isEqualTo(findMember.getId());
    }
}
