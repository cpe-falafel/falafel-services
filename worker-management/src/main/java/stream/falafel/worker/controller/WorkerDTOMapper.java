package stream.falafel.worker.controller;

import cpe.commons.api.worker.CreateWorkerDTO;
import cpe.commons.api.worker.SingleWorkerDTO;
import cpe.commons.api.worker.WorkerListDTO;
import cpe.commons.api.worker.WorkerSummary;
import stream.falafel.worker.domain.worker.Worker;

import java.util.List;
import java.util.stream.Collectors;

public class WorkerDTOMapper {

    /**
     * Maps a Worker domain object to a SingleWorkerDTO.
     *
     * @param worker the Worker domain object
     * @return a SingleWorkerDTO representing the Worker
     */
    public static SingleWorkerDTO toSingleWorkerDTO(Worker worker) {
        return new SingleWorkerDTO(
                worker.getUid(),
                worker.getGroupUid() != null ? worker.getGroupUid().toString() : null,
                worker.getLastUpdate().toEpochDay(),
                worker.getConfigurationValue(),
                worker.getPreviewUri(),
                worker.getPreviewKey()
        );
    }

    /**
     * Maps a list of Worker objects to a WorkerListDTO.
     *
     * @param workers the list of Worker objects
     * @return a WorkerListDTO containing summary information about the workers
     */
    public static WorkerListDTO toWorkerListDTO(List<Worker> workers) {
        List<WorkerSummary> summaries = workers.stream()
                .map(worker -> new WorkerSummary(
                        worker.getUid().toString(),
                        worker.getLastUpdate().toEpochDay(),
                        worker.getUri()
                ))
                .collect(Collectors.toList());
        return new WorkerListDTO(summaries);
    }

    /**
     * Maps a CreateWorkerDTO to a Worker domain object.
     *
     * @param dto the CreateWorkerDTO
     * @return a Worker domain object
     */
    public static Worker toWorker(CreateWorkerDTO dto) {
        return new Worker(
                dto.getConfigurationValue(),
                dto.getUri(),
                dto.getApiKey()
        );
    }
}

