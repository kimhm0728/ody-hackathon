package org.woowacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woowacourse.domain.Mate;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {

}
