package org.woowacourse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woowacourse.domain.Mate;
import org.woowacourse.repository.MateRepository;

@Service
@RequiredArgsConstructor
public class MateService {

    private final MateRepository mateRepository;

    public Mate save(Mate mate) {
        return mateRepository.save(mate);
    }
}
