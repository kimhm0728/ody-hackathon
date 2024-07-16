package org.woowacourse.service;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;
import org.woowacourse.domain.NotificationType;

@Service
public class FcmService {

    public String sendPushNotification(String token, NotificationType notificationType, String title, String body)
            throws Exception {
        // DB에 저장된 정보로 요청을 보낸 회원의 token을 찾아 setToken()에 넣어야함.

        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .putData("type", notificationType.name())
                .setAndroidConfig(
                        AndroidConfig.builder()
                                .setNotification(
                                        AndroidNotification.builder()
                                                .setTitle(title)
                                                .setBody(body)
                                                .setClickAction("push_click")
                                                .build()
                                ).build()
                ).build();

        return FirebaseMessaging.getInstance().send(message);
    }
}
