package cpe.workermanagement.domain;

import cpe.commons.api.flux.FluxDTO;
import cpe.workermanagement.domain.flux.FluxService;
import cpe.workermanagement.domain.worker.Worker;
import cpe.workermanagement.domain.worker.WorkerService;
import cpe.workermanagement.exception.CommitException;
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

        FluxDTO existingFlux = fluxService.getFluxByUid(fluxId);
        if (existingFlux == null) {
            throw new CommitException();
        }

        // TODO :: implement..
    }
}
