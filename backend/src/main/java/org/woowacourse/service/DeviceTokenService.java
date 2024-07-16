package org.woowacourse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woowacourse.domain.DeviceToken;
import org.woowacourse.domain.Member;
import org.woowacourse.repository.DeviceTokenRepository;

@Service
@RequiredArgsConstructor
public class DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;

    public DeviceToken save(String token, Member member) {
        DeviceToken deviceToken = DeviceToken.builder()
                .token(token)
                .member(member)
                .build();

        return deviceTokenRepository.save(deviceToken);
    }
}
