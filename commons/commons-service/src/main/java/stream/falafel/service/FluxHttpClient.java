package stream.falafel.service;

import cpe.commons.api.flux.*;
import cpe.commons.api.worker.WorkerListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class FluxHttpClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public FluxHttpClient(String fluxManagerBaseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = fluxManagerBaseUrl;
    }


    public SingleFluxDTO getFluxByUid(UUID uuid) {
        String uid = uuid.toString(); // Flux use String for the parameters

        String url = baseUrl + "/flux/" + uid;

        try {
            ResponseEntity<SingleFluxDTO> response = restTemplate.getForEntity(url, SingleFluxDTO.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Réponse reçue : " + response.getBody());
                return response.getBody();
            } else {
                System.out.println("Erreur : " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<FluxListSummary> findFluxByOwner(String group) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("group", group)
                .toUriString();

        try {
            ResponseEntity<FluxListSummary[]> response = restTemplate.getForEntity(url, FluxListSummary[].class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Arrays.asList(response.getBody());
            } else {
                System.out.println("Erreur : " + response.getStatusCode());
                return Collections.emptyList();  // Renvoie une liste vide si la réponse n'est pas correcte
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void createFlux(CreateFluxDTO createFluxDTO) {
        String url = baseUrl + "/flux";

        try {
            restTemplate.postForEntity(url, createFluxDTO, Void.class);
            System.out.println("Création du flux réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du flux.");
        }
    }

    public void editFlux(String uid, EditFluxDTO editFluxDTO) {
        String url = baseUrl + "/flux/" + uid;

        try {
            restTemplate.put(url, editFluxDTO);
            System.out.println("Modification du flux réussie pour UID : " + uid);
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification du flux.");
        }
    }
}
