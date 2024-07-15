package org.woowacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.woowacourse.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch DeviceToken d on m.id = d.member.id where d.token = :token")
    Member findByToken(String token);
}

