package org.woowacourse.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.woowacourse.domain.Member;
import org.woowacourse.domain.Token;
import org.woowacourse.dto.request.TokenRequest;
import org.woowacourse.repository.TokenRepository;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token save(TokenRequest tokenRequest, Member member) {
        Token token = new Token(tokenRequest.value(), member);
        return tokenRepository.save(token);
    }

    public List<Token> findAll() {
        return tokenRepository.findAll();
    }
}
