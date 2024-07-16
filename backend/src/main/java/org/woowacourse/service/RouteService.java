package org.woowacourse.service;

import org.springframework.stereotype.Service;
import org.woowacourse.domain.Coordinates;
import org.woowacourse.domain.Duration;

@Service
public class RouteService {

    private final RouteClient routeClient;

    public RouteService(RouteClient routeClient) {
        this.routeClient = routeClient;
    }

    public Duration calcualteDuration(Coordinates origin, Coordinates target) {
        return routeClient.calculateDuration(origin, target);
    }
}
