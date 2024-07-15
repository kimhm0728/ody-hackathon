package org.woowacourse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.woowacourse.controller.dto.request.FcmSendRequest;
import org.woowacourse.domain.NotificationType;
import org.woowacourse.service.FcmService;

@RestController
@RequiredArgsConstructor
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody FcmSendRequest fcmSendRequest) {
        try {
            return fcmService.sendPushNotification(fcmSendRequest.token(), NotificationType.DEPARTURE,
                    fcmSendRequest.title(), fcmSendRequest.body());
        } catch (Exception e) {
            return "Error sending notification: " + e.getMessage();
        }
    }
}
