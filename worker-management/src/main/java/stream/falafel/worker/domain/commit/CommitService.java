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

import java.util.UUID;

@Service
@AllArgsConstructor
public class CommitService {

    private final WorkerService workerService;
    private final FluxService fluxService;
    private final RestTemplate restTemplate;

    public void commit(UUID workerId, UUID fluxId) throws CommitException {

        String baseUrl = "someURL/worker/";

        Worker existingWorker = workerService.getWorkerByUid(workerId);
        if (existingWorker == null) {
            throw new CommitException();
        }

        SingleFluxDTO existingFlux = fluxService.getFluxByUid(fluxId);
        if (existingFlux == null) {
            throw new CommitException();
        }

        Commit commit = new Commit(existingFlux, existingWorker); //Object to combine flux and worker

        try {
            restTemplate.postForEntity(baseUrl, commit, Void.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // TODO :: test and define exactly what to send..
    }
}
