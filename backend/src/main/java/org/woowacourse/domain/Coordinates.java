package org.woowacourse.domain;

import lombok.Getter;

@Getter
public class Coordinates {

    private final String latitude;
    private final String longitude;

    public Coordinates(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
