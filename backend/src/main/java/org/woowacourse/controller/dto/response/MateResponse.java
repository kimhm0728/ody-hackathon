package org.woowacourse.controller.dto.response;

import org.woowacourse.domain.Mate;

public record MateResponse(String nickname) {

    public static MateResponse toResponse(Mate mate) {
        return new MateResponse(mate.getNickname());
    }
}
