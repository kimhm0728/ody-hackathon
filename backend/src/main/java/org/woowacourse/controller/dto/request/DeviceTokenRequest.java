package org.woowacourse.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DeviceTokenRequest(
        @NotBlank(message = "토큰 값이 비었습니다.")
        @Size(max = 255, message = "토큰은 255자 이하여야 합니다.")
        String value
) {

}
