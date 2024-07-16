package org.woowacourse.domain;

import lombok.Getter;

@Getter
public class Duration{

    private final int minutes;

    public Duration(int minutes) {
        this.minutes = minutes;
    }
}
