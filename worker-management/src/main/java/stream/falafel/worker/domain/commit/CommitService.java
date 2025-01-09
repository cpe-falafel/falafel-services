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

import java.util.UUID;

@Service
@AllArgsConstructor
public class CommitService {

    private final WorkerService workerService;
    private final FluxService fluxService;
    private final RestTemplate restTemplate;
    private final WorkerRepository workerRepository;

    public void commit(UUID workerId, UUID fluxId) throws CommitException {

        String baseUrl = "someURL/worker/"; // Need eliot input

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

        // Commit commit = new Commit(existingFlux, existingWorker); //Object to combine flux and worker

        try {
            restTemplate.postForEntity(baseUrl, saved, Void.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // TODO :: test
    }
}
