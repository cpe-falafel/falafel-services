package stream.falafel.worker.domain;

import cpe.commons.api.flux.SingleFluxDTO;
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

    public void commit(UUID workerId, UUID fluxId) throws CommitException {

        Worker existingWorker = workerService.getWorkerByUid(workerId);
        if (existingWorker == null) {
            throw new CommitException();
        }

        SingleFluxDTO existingFlux = fluxService.getFluxByUid(fluxId);
        if (existingFlux == null) {
            throw new CommitException();
        }

        // TODO :: implement..
    }
}
