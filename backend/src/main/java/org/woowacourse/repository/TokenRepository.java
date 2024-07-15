package org.woowacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woowacourse.domain.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
