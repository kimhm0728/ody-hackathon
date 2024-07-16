package org.woowacourse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.woowacourse.domain.Coordinates;
import org.woowacourse.domain.Duration;

@Component
public class RouteClient {

    private final String key;
    private final String url;
    private final RestTemplate restTemplate;

    public RouteClient(
            @Value("${odsay.secret-key}") String key,
            @Value("${odsay.url}") String url,
            RestTemplate restTemplate
    ) {
        this.key = key;
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public Duration calculateDuration(Coordinates origin, Coordinates target) {
        String urlInfo = makeURL(origin, target);
        String responses = restTemplate.getForObject(urlInfo, String.class);
        int minutes = parse(responses);
        return new Duration(minutes);
    }

    private String makeURL(Coordinates origin, Coordinates target) {
        try {
            return url
                    + "?SY=" + origin.getLatitude()
                    + "&SX=" + origin.getLongitude()
                    + "&EY=" + target.getLatitude()
                    + "&EX=" + target.getLongitude()
                    + "&apiKey="
                    + URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode 오류");
        }
    }

    int parse(String result) {
        JsonNode routeNode = null;
        try {
            routeNode = new ObjectMapper().readTree(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return routeNode.get("result")
                .get("path")
                .get(0)
                .get("info")
                .get("totalTime")
                .asInt();
    }
}
