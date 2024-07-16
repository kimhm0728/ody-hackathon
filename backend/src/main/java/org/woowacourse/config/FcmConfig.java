package org.woowacourse.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Slf4j
public class FcmConfig {

    @Value("${fcm.enabled}")
    private boolean fcmEnabled;

    @PostConstruct
    public void initialize() {
        if (!fcmEnabled) {
            log.info("Fcm 설정이 비활성화되었습니다.");
            return;
        }

        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(new ClassPathResource(
                                    "ody-hackathon-firebase-adminsdk-6uscb-594231ea72.json").getInputStream()))
                    .build();

            FirebaseApp.initializeApp(options);
            log.info("Fcm 설정 성공");
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }
}
