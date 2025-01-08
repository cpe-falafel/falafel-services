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

        //String baseUrl = "someURL/worker/";

        Worker existingWorker = workerService.getWorkerByUid(workerId);
        if (existingWorker == null) {
            throw new CommitException();
        }

        SingleFluxDTO existingFlux = fluxService.getFluxByUid(fluxId);
        if (existingFlux == null) {
            throw new CommitException();
        }

        /*

        POST {{WorkerApi_HostAddress}}/worker/
            Accept: application/json
            Content-Type: application/json
            {
                "jsonWorkerConfiguration": "{"in1":{"type":"_IN","in":[],"out":["stream_1"],"properties":{"src":"http"}},"filter1":{"type":"drawbox","in":["stream_1"],"out":["stream_2"],"properties":{}},"out1":{"type":"_OUT","in":["stream_2"],"out":[],"properties":{}}}"
            }
         */

        // TODO :: implement..
    }
}
