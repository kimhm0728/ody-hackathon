package org.woowacourse.controller.dto.response;

import java.util.Collection;

public record MultipleResponses<T>(Collection<T> responses) {

}
