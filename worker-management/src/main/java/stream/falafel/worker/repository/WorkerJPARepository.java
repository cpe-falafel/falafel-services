package stream.falafel.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerJPARepository extends JpaRepository<WorkerEntity, String> {

    /**
     * Finds a worker entity by its URI.
     *
     * @param uri the URI of the worker
     * @return an Optional containing the worker entity, if found
     */
    Optional<WorkerEntity> findByUri(String uri);

    /**
     * Finds all worker entities that belong to a specific group.
     *
     * @param groupUid the UID of the group
     * @return a list of worker entities in the group
     */
    List<WorkerEntity> findAllByGroupUid(String groupUid);

    /**
     * Deletes a worker entity by its UID.
     *
     * @param uid the UID of the worker to delete
     */
    void deleteByUid(String uid);
}

