package cpe.workermanagement.repository;

import cpe.workermanagement.domain.worker.Worker;

import java.util.UUID;

public class WorkerEntityMapper {

    public static Worker toDomain(WorkerEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Worker(
                UUID.fromString(entity.getUid()),
                entity.getGroupUid() != null ? UUID.fromString(entity.getGroupUid()) : null,
                entity.getLastUpdate(),
                entity.getConfigurationValue(),
                entity.getUri(),
                entity.getApiKey(),
                entity.getPreviewUri(),
                entity.getPreviewKey(),
                entity.getPackageKey()
        );
    }

    public static WorkerEntity toEntity(Worker domain) {
        if (domain == null) {
            return null;
        }
        return new WorkerEntity(
                domain.getUid().toString(),
                domain.getGroupUid() != null ? domain.getGroupUid().toString() : null,
                domain.getLastUpdate(),
                domain.getConfigurationValue(),
                domain.getUri(),
                domain.getApiKey(),
                domain.getPreviewUri(),
                domain.getPreviewKey(),
                domain.getPackageKey()
        );
    }
}
