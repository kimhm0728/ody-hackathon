package org.woowacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woowacourse.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
