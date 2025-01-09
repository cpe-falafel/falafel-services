package stream.falafel.worker.controller;

import cpe.commons.api.worker.CreateWorkerDTO;
import cpe.commons.api.worker.SingleWorkerDTO;
import cpe.commons.api.worker.WorkerListDTO;
import stream.falafel.worker.domain.CommitService;
import stream.falafel.worker.domain.worker.Worker;

import java.util.List;
import java.util.UUID;

import stream.falafel.worker.domain.worker.WorkerService;
import stream.falafel.worker.exception.CommitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;
    private final CommitService commitService;

    public WorkerController(WorkerService workerService, CommitService commitService) {
        this.workerService = workerService;
        this.commitService = commitService;
    }

    /**
     * Get a list of workers.
     *
     * @param group the name of the group to filter workers (it can be "null")
     * @return WorkerListDTO containing all workers.
     */
    @GetMapping
    public ResponseEntity<WorkerListDTO> getAllWorkers(@RequestParam String group) {
        List<Worker> workers = workerService.getAllWorkers(group);
        if (workers.isEmpty())
            return ResponseEntity.notFound().build();
        WorkerListDTO workerListDTO = WorkerDTOMapper.toWorkerListDTO(workers);
        return ResponseEntity.ok(workerListDTO);
    }

    /**
     * Get details of a single worker.
     *
     * @param uid the worker's UID.
     * @return SingleWorkerDTO with worker details.
     */
    @GetMapping("/{uid}")
    public ResponseEntity<SingleWorkerDTO> getWorkerById(@PathVariable UUID uid) {
        Worker worker = workerService.getWorkerByUid(uid);
        if (worker == null) {
            return ResponseEntity.notFound().build();
        }
        SingleWorkerDTO singleWorkerDTO = WorkerDTOMapper.toSingleWorkerDTO(worker);
        return ResponseEntity.ok(singleWorkerDTO);
    }

    /**
     * Create a new worker.
     *
     * @param createWorkerDTO the data for the new worker.
     * @return the created SingleWorkerDTO.
     */
    @PostMapping
    public ResponseEntity<SingleWorkerDTO> createWorker(@RequestBody CreateWorkerDTO createWorkerDTO) {
        Worker newWorker = WorkerDTOMapper.toWorker(createWorkerDTO);
        Worker savedWorker = workerService.createWorker(newWorker);
        SingleWorkerDTO singleWorkerDTO = WorkerDTOMapper.toSingleWorkerDTO(savedWorker);
        return ResponseEntity.status(HttpStatus.CREATED).body(singleWorkerDTO);
    }

    /**
     * Delete an existing worker.
     *
     * @param uid the worker's UID.
     * @return a 204 No Content status if the deletion is successful.
     */
    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteWorker(@PathVariable UUID uid) {
        Worker existingWorker = workerService.getWorkerByUid(uid);
        if (existingWorker == null) {
            return ResponseEntity.notFound().build();
        }
        workerService.deleteWorker(uid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uid}/flux/{fluxId}")
    public ResponseEntity<Void> commitFlux(@PathVariable UUID uid, @PathVariable UUID fluxId) throws CommitException {
        commitService.commit(uid, fluxId);
        return ResponseEntity.ok().build();
    }
}

