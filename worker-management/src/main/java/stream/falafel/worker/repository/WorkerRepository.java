package stream.falafel.worker.repository;

import stream.falafel.worker.domain.worker.Worker;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class WorkerRepository {

    private final WorkerJPARepository jpaRepository;

    public WorkerRepository(WorkerJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Saves a worker to the database.
     *
     * @param worker the domain model of the worker
     * @return the saved worker with updated details
     */
    public Worker save(Worker worker) {
        WorkerEntity entity = WorkerEntityMapper.toEntity(worker);
        WorkerEntity savedEntity = jpaRepository.save(entity);
        return WorkerEntityMapper.toDomain(savedEntity);
    }

    /**
     * Finds a worker by its UID.
     *
     * @param uid the UID of the worker
     * @return an Optional containing the worker, if found
     */
    public Optional<Worker> findByUid(UUID uid) {
        return jpaRepository.findById(uid.toString())
                .map(WorkerEntityMapper::toDomain);
    }

    /**
     * Finds all workers in the database.
     *
     * @return a list of all workers as domain models
     */
    public List<Worker> findAll() {
        return jpaRepository.findAll().stream()
                .map(WorkerEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Finds workers by group UID.
     *
     * @param groupUid the group UID
     * @return a list of workers belonging to the group
     */
    public List<Worker> findByGroupUid(UUID groupUid) {
        return jpaRepository.findAllByGroupUid(groupUid.toString()).stream()
                .map(WorkerEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a worker by its UID.
     *
     * @param uid the UID of the worker to delete
     */
    public void deleteByUid(UUID uid) {
        jpaRepository.deleteByUid(uid.toString());
    }
}

