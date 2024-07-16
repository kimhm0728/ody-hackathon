package org.woowacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woowacourse.domain.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
