package org.woowacourse.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.woowacourse.domain.Coordinates;
import org.woowacourse.domain.Duration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RouteServiceTest {

    @Autowired
    RouteService routeService;

    @DisplayName("소요시간 정상 반환")
    @Test
    void calculateDuration() {
        Coordinates origin = new Coordinates("37.508615", "126.890771");
        Coordinates target = new Coordinates("37.504953", "127.048643");

        Duration duration = routeService.calcualteDuration(origin, target);

        assertThat(duration.getMinutes()).isPositive();
    }
}
