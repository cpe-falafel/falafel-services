package stream.falafel.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserHttpClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public UserHttpClient(RestTemplate restTemplate, String userManagerBaseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = userManagerBaseUrl;
    }


}

