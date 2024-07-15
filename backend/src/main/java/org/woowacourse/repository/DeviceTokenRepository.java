package org.woowacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woowacourse.domain.DeviceToken;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {

}
