package stream.falafel.worker.repository;

import stream.falafel.worker.domain.worker.Worker;

import java.util.UUID;

public class WorkerEntityMapper {

  public static Worker toDomain(WorkerEntity entity) {
    if (entity == null) {
      return null;
    }
    return new Worker(
        UUID.fromString(entity.getUid()),
        entity.getGroupUid(),
        entity.getLastFluxUid(),
        entity.getLastUpdate(),
        entity.getConfigurationValue(),
        entity.getUri(),
        entity.getApiKey(),
        entity.getPreviewUri(),
        entity.getPreviewKey(),
        entity.getPackageKey());
  }

  public static WorkerEntity toEntity(Worker domain) {
    if (domain == null) {
      return null;
    }
    return new WorkerEntity(
        domain.getUid().toString(),
        domain.getGroupUid(),
        domain.getLastFluxUid(),
        domain.getLastUpdate(),
        domain.getConfigurationValue(),
        domain.getUri(),
        domain.getApiKey(),
        domain.getPreviewUri(),
        domain.getPreviewKey(),
        domain.getPackageKey());
  }
}
