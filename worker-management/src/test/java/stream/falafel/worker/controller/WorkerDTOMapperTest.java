package stream.falafel.worker.controller;

import cpe.commons.api.worker.CreateWorkerDTO;
import cpe.commons.api.worker.SingleWorkerDTO;
import cpe.commons.api.worker.WorkerListDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stream.falafel.worker.domain.worker.Worker;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerDTOMapperTest {

  @Test
  void toSingleWorkerDTO_shouldMapWorkerToSingleWorkerDTO() {
    // Arrange
    Worker worker =
        new Worker(
            UUID.randomUUID(),
            UUID.randomUUID(),
            "1",
            LocalDate.of(2025, 1, 1),
            "config-value",
            "http://worker/api",
            "api-key",
            "http://worker/preview",
            "preview-key",
            "package-key");

    // Act
    SingleWorkerDTO dto = WorkerDTOMapper.toSingleWorkerDTO(worker);

    // Assert
    assertEquals(worker.getUid(), dto.getUid());
    assertEquals(worker.getGroupUid(), dto.getGroup());
    assertEquals(LocalDate.of(2025, 1, 1).toEpochDay(), dto.getLastUpdate());
    assertEquals("config-value", dto.getConfigurationValue());
    assertEquals("http://worker/preview", dto.getPreviewUri());
    assertEquals("preview-key", dto.getPreviewKey());
  }

  @Test
  void toWorkerListDTO_shouldMapWorkersToWorkerListDTO() {
    // Arrange
    Worker worker1 =
        new Worker(
            UUID.randomUUID(),
            UUID.randomUUID(),
            "1",
            LocalDate.of(2025, 1, 1),
            "config-value",
            "http://worker1/api",
            "api-key",
            "http://worker1/preview",
            "preview-key",
            "package-key");

    Worker worker2 =
        new Worker(
            UUID.randomUUID(),
            UUID.randomUUID(),
            "1",
            LocalDate.of(2025, 1, 2),
            "config-value",
            "http://worker2/api",
            "api-key",
            "http://worker2/preview",
            "preview-key",
            "package-key");

    List<Worker> workers = Arrays.asList(worker1, worker2);

    // Act
    WorkerListDTO dto = WorkerDTOMapper.toWorkerListDTO(workers);

    // Assert
    assertNotNull(dto.getValues());
    assertEquals(2, dto.getValues().size());
    assertEquals(worker1.getUid(), dto.getValues().get(0).getUid());
    assertEquals(LocalDate.of(2025, 1, 1).toEpochDay(), dto.getValues().get(0).getLastUpdate());
    assertEquals("http://worker1/api", dto.getValues().get(0).getUri());
    assertEquals(worker2.getUid(), dto.getValues().get(1).getUid());
    assertEquals(LocalDate.of(2025, 1, 2).toEpochDay(), dto.getValues().get(1).getLastUpdate());
    assertEquals("http://worker2/api", dto.getValues().get(1).getUri());
  }

  @Test
  void toWorker_shouldMapCreateWorkerDTOToWorker() {
    // Arrange
    CreateWorkerDTO dto = Mockito.mock(CreateWorkerDTO.class);
    Mockito.when(dto.getConfigurationValue()).thenReturn("config-value");
    Mockito.when(dto.getUri()).thenReturn("http://example.com/worker");
    Mockito.when(dto.getApiKey()).thenReturn("api-key");

    // Act
    Worker worker = WorkerDTOMapper.toWorker(dto);

    // Assert
    assertEquals("config-value", worker.getConfigurationValue());
    assertEquals("http://example.com/worker", worker.getUri());
    assertEquals("api-key", worker.getApiKey());
  }
}
