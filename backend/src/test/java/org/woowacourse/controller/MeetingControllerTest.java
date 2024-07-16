package org.woowacourse.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.woowacourse.controller.dto.request.MeetingRequest;
import org.woowacourse.domain.DeviceToken;
import org.woowacourse.domain.Member;
import org.woowacourse.repository.DeviceTokenRepository;
import org.woowacourse.repository.MemberRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeetingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DeviceTokenRepository deviceTokenRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("모임방을 개설한다.")
    @Test
    void save() {
        MeetingRequest meetingRequest = new MeetingRequest(
                "우테코 16조",
                LocalDate.parse("2024-07-15"),
                LocalTime.parse("14:00"),
                "서울 송파구 올림픽로35다길 42",
                "37.515298",
                "127.103113",
                "오디",
                "서울 강남구 테헤란로 411",
                "37.505713",
                "127.050691"
        );

        Member member = memberRepository.save(Member.builder().build());
        deviceTokenRepository.save(DeviceToken.builder()
                .member(member)
                .token("test")
                .build());

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "test")
                .body(meetingRequest)
                .accept(ContentType.JSON)
                .when()
                .post("/meetings")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }
}
