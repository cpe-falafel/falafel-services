package stream.falafel.service;

import cpe.commons.api.worker.CreateWorkerDTO;
import cpe.commons.api.worker.SingleWorkerDTO;
import cpe.commons.api.worker.WorkerListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class WorkerHttpClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public WorkerHttpClient(RestTemplate restTemplate, String workerManagerBaseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = workerManagerBaseUrl;
    }

    /**
     * Get a list of workers filtered by group.
     *
     * @param group the group name to filter workers (can be null).
     * @return WorkerListDTO containing workers.
     */
    public WorkerListDTO getAllWorkers(String group) {
        String url = baseUrl + "?group=" + (group != null ? group : "");
        ResponseEntity<WorkerListDTO> response = restTemplate.getForEntity(url, WorkerListDTO.class);
        return response.getBody();
    }

    /**
     * Get details of a single worker by UID.
     *
     * @param uid the worker's UID.
     * @return SingleWorkerDTO with worker details.
     */
    public SingleWorkerDTO getWorkerById(UUID uid) {
        String url = baseUrl + "/" + uid;
        ResponseEntity<SingleWorkerDTO> response = restTemplate.getForEntity(url, SingleWorkerDTO.class);
        return response.getBody();
    }

    /**
     * Create a new worker.
     *
     * @param createWorkerDTO the data for the new worker.
     * @return the created SingleWorkerDTO.
     */
    public SingleWorkerDTO createWorker(CreateWorkerDTO createWorkerDTO) {
        ResponseEntity<SingleWorkerDTO> response = restTemplate.postForEntity(baseUrl, createWorkerDTO, SingleWorkerDTO.class);
        return response.getBody();
    }

    /**
     * Delete an existing worker by UID.
     *
     * @param uid the worker's UID.
     */
    public void deleteWorker(UUID uid) {
        String url = baseUrl + "/" + uid;
        restTemplate.delete(url);
    }

    /**
     * Commit a flux to a worker.
     *
     * @param uid    the worker's UID.
     * @param fluxId the flux ID.
     */
    public void commitFlux(UUID uid, UUID fluxId) {
        String url = baseUrl + "/" + uid + "/flux/" + fluxId;
        restTemplate.put(url, null);
    }
}

