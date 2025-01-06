package stream.falafel.worker.domain.worker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stream.falafel.worker.repository.WorkerRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkerServiceTest {

  @Mock private WorkerRepository workerRepository;

  @InjectMocks private WorkerService workerService;

  private Worker mockWorker;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockWorker =
        new Worker(
            UUID.randomUUID(),
            UUID.randomUUID(),
            LocalDate.of(2025, 1, 1),
            "config-value",
            "http://worker/api",
            "api-key",
            "http://worker/preview",
            "preview-key",
            "package-key");
  }

  @Test
  void testCreateWorker_Success() {
    when(workerRepository.save(any(Worker.class))).thenReturn(mockWorker);

    Worker createdWorker = workerService.createWorker(mockWorker);

    assertNotNull(createdWorker);
    assertEquals(mockWorker.getUid(), createdWorker.getUid());
    verify(workerRepository, times(1)).save(mockWorker);
  }

  @Test
  void testCreateWorker_ValidationFails() {
    mockWorker.setConfigurationValue(null);

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              workerService.createWorker(mockWorker);
            });

    assertEquals("Configuration value cannot be null or empty.", exception.getMessage());
    verify(workerRepository, never()).save(any(Worker.class));
  }

  @Test
  void testGetWorkerByUid_Found() {
    UUID uid = mockWorker.getUid();
    when(workerRepository.findByUid(uid)).thenReturn(Optional.of(mockWorker));

    Worker foundWorker = workerService.getWorkerByUid(uid);

    assertNotNull(foundWorker);
    assertEquals(uid, foundWorker.getUid());
    verify(workerRepository, times(1)).findByUid(uid);
  }

  @Test
  void testGetWorkerByUid_NotFound() {
    UUID uid = UUID.randomUUID();
    when(workerRepository.findByUid(uid)).thenReturn(Optional.empty());

    Worker foundWorker = workerService.getWorkerByUid(uid);

    assertNull(foundWorker);
    verify(workerRepository, times(1)).findByUid(uid);
  }

  @Test
  void testGetAllWorkers_NoGroup() {
    when(workerRepository.findAll()).thenReturn(Collections.singletonList(mockWorker));

    List<Worker> workers = workerService.getAllWorkers(null);

    assertNotNull(workers);
    assertEquals(1, workers.size());
  }

  @Test
  void testGetAllWorkers_WithGroup() {
    Worker anotherWorker =
        new Worker(
            UUID.randomUUID(),
            mockWorker.getGroupUid(),
            LocalDate.of(2025, 1, 2),
            "config-value-2",
            "http://worker2/api",
            "api-key-2",
            "http://worker2/preview",
            "preview-key-2",
            "package-key-2");

    when(workerRepository.findAll()).thenReturn(Arrays.asList(mockWorker, anotherWorker));

    List<Worker> workers = workerService.getAllWorkers(mockWorker.getGroupUid().toString());

    assertNotNull(workers);
    assertEquals(2, workers.size());
    assertEquals(mockWorker.getUid(), workers.get(0).getUid());
  }

  @Test
  void testUpdateWorker_Success() {
    UUID uid = mockWorker.getUid();
    when(workerRepository.findByUid(uid)).thenReturn(Optional.of(mockWorker));
    when(workerRepository.save(any(Worker.class))).thenReturn(mockWorker);

    Worker updatedWorker =
        new Worker(
            uid,
            UUID.randomUUID(),
            LocalDate.of(2025, 2, 1),
            "new-config",
            "http://newexample.com",
            "new-api-key",
            "http://newpreview.com",
            "new-preview-key",
            "new-package-key");

    Worker result = workerService.updateWorker(uid, updatedWorker);

    assertNotNull(result);
    assertEquals("new-config", result.getConfigurationValue());
    verify(workerRepository, times(1)).findByUid(uid);
    verify(workerRepository, times(1)).save(mockWorker);
  }

  @Test
  void testUpdateWorker_NotFound() {
    UUID uid = UUID.randomUUID();
    when(workerRepository.findByUid(uid)).thenReturn(Optional.empty());

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              workerService.updateWorker(uid, mockWorker);
            });

    assertEquals("Worker with UID " + uid + " not found.", exception.getMessage());
    verify(workerRepository, times(1)).findByUid(uid);
    verify(workerRepository, never()).save(any(Worker.class));
  }

  @Test
  void testDeleteWorker_Success() {
    UUID uid = mockWorker.getUid();
    when(workerRepository.findByUid(uid)).thenReturn(Optional.of(mockWorker));

    workerService.deleteWorker(uid);

    verify(workerRepository, times(1)).findByUid(uid);
    verify(workerRepository, times(1)).deleteByUid(uid);
  }

  @Test
  void testDeleteWorker_NotFound() {
    UUID uid = UUID.randomUUID();
    when(workerRepository.findByUid(uid)).thenReturn(Optional.empty());

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              workerService.deleteWorker(uid);
            });

    assertEquals("Worker with UID " + uid + " not found.", exception.getMessage());
    verify(workerRepository, times(1)).findByUid(uid);
    verify(workerRepository, never()).deleteByUid(any(UUID.class));
  }
}
