package stream.falafel.service;

import cpe.commons.api.flux.FluxDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class FluxHttpClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public FluxHttpClient(String fluxManagerBaseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = fluxManagerBaseUrl;
    }

    public FluxDTO getFluxByUid(UUID uuid) {
        // TODO
        return null;
    }

    // TODO :: get all controller methods
}
