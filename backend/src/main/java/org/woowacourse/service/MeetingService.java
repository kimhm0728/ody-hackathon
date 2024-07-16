package org.woowacourse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woowacourse.domain.Meeting;
import org.woowacourse.repository.MeetingRepository;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public Meeting save(Meeting meeting) {
        return meetingRepository.save(meeting);
    }
}
