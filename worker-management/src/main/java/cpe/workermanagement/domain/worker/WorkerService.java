package cpe.workermanagement.domain.worker;

import cpe.workermanagement.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    /**
     * Creates and saves a new worker.
     *
     * @param worker the worker to be saved
     * @return the saved worker
     */
    public Worker createWorker(Worker worker) {
        validateWorker(worker);
        return workerRepository.save(worker);
    }

    /**
     * Finds a worker by its UID.
     *
     * @param uid the UID of the worker
     * @return a worker if found
     */
    public Worker getWorkerByUid(UUID uid) {
        Optional<Worker> optionalWorker = workerRepository.findByUid(uid);
        return optionalWorker.orElse(null);
    }

    /**
     * Retrieves all workers.
     *
     * @return a list of all workers
     */
    public List<Worker> getAllWorkers(String group) {
        List<Worker> workers = workerRepository.findAll();

        if (group != null) {
            workers = workers.stream().filter(worker -> worker.getGroupUid().toString().equals(group)).toList();
        }

        return workerRepository.findAll();
    }

    /**
     * Updates an existing worker.
     *
     * @param uid    the UID of the worker to update
     * @param worker the updated worker data
     * @return the updated worker, or throws an exception if not found
     */
    public Worker updateWorker(UUID uid, Worker worker) {
        Optional<Worker> existingWorker = workerRepository.findByUid(uid);

        if (existingWorker.isEmpty()) {
            throw new IllegalArgumentException("Worker with UID " + uid + " not found.");
        }

        // Ensure we keep the same UID and update only relevant fields
        Worker workerToUpdate = existingWorker.get();
        workerToUpdate.setGroupUid(worker.getGroupUid());
        workerToUpdate.setLastUpdate(worker.getLastUpdate());
        workerToUpdate.setConfigurationValue(worker.getConfigurationValue());
        workerToUpdate.setUri(worker.getUri());
        workerToUpdate.setApiKey(worker.getApiKey());
        workerToUpdate.setPreviewUri(worker.getPreviewUri());
        workerToUpdate.setPreviewKey(worker.getPreviewKey());
        workerToUpdate.setPackageKey(worker.getPackageKey());

        return workerRepository.save(workerToUpdate);
    }

    /**
     * Deletes a worker by its UID.
     *
     * @param uid the UID of the worker to delete
     */
    public void deleteWorker(UUID uid) {
        Optional<Worker> existingWorker = workerRepository.findByUid(uid);
        if (existingWorker.isEmpty()) {
            throw new IllegalArgumentException("Worker with UID " + uid + " not found.");
        }
        workerRepository.deleteByUid(uid);
    }

    /**
     * Validates the worker data.
     *
     * @param worker the worker to validate
     */
    private void validateWorker(Worker worker) {
        if (worker.getConfigurationValue() == null || worker.getConfigurationValue().isEmpty()) {
            throw new IllegalArgumentException("Configuration value cannot be null or empty.");
        }
        if (worker.getUri() == null || worker.getUri().isEmpty()) {
            throw new IllegalArgumentException("URI cannot be null or empty.");
        }
    }
}

