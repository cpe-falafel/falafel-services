package stream.falafel.worker.domain.flux;

import cpe.commons.api.flux.FluxDTO;
import stream.falafel.service.FluxHttpClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FluxService {
    private FluxHttpClient fluxHttpClient;

    @Value("${flux.manager.url}")
    private String fluxManagerBaseUrl;

    @PostConstruct
    private void init() {
        fluxHttpClient = new FluxHttpClient(fluxManagerBaseUrl);
    }

    public FluxDTO getFluxByUid(UUID uuid){
        return fluxHttpClient.getFluxByUid(uuid);
    }
}
