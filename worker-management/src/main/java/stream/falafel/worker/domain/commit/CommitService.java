package stream.falafel.worker.domain.commit;

import cpe.commons.api.flux.SingleFluxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import stream.falafel.worker.domain.flux.FluxService;
import stream.falafel.worker.domain.worker.Worker;
import stream.falafel.worker.domain.worker.WorkerService;
import stream.falafel.worker.exception.CommitException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stream.falafel.worker.repository.WorkerRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommitService {

    private final WorkerService workerService;
    private final FluxService fluxService;
    private final RestTemplate restTemplate;
    private final WorkerRepository workerRepository;

    public void commit(UUID workerId, UUID fluxId) throws CommitException {

        Worker existingWorker = workerService.getWorkerByUid(workerId);
        if (existingWorker == null) {
            throw new CommitException();
        }

        SingleFluxDTO existingFlux = fluxService.getFluxByUid(fluxId);
        if (existingFlux == null) {
            throw new CommitException();
        }

        existingWorker.setLastFluxUid(fluxId.toString());
        existingWorker.setConfigurationValue(existingFlux.getValue());
        Worker saved = workerRepository.save(existingWorker);


        Commit commit = new Commit(saved.getConfigurationValue()); // Object to combine flux and worker

        URI uri = null;
        try {
            uri = new URI(saved.getUri());
            uri = new URI(uri.getScheme(), uri.getHost(), "/worker/", uri.getFragment());
        } catch (URISyntaxException e) {
            throw new CommitException(e);
        }

        try {
            restTemplate.postForEntity( uri, commit, Void.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // TODO :: test
    }
}
