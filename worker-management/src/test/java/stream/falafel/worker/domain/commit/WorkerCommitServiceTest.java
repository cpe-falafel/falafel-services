package stream.falafel.worker.domain.commit;

import cpe.commons.api.flux.SingleFluxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stream.falafel.worker.domain.flux.FluxService;
import stream.falafel.worker.domain.worker.Worker;
import stream.falafel.worker.domain.worker.WorkerService;
import stream.falafel.worker.exception.CommitException;
import stream.falafel.worker.repository.WorkerRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WorkerCommitServiceTest {

  @Mock private WorkerService workerService;

  @Mock private FluxService fluxService;

  @Mock
  private WorkerRepository workerRepository;

  @InjectMocks private WorkerCommitService workerCommitService;

  private UUID validWorkerId;
  private UUID validFluxId;
  private Worker mockWorker;
  private SingleFluxDTO mockFlux;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    validWorkerId = UUID.randomUUID();
    validFluxId = UUID.randomUUID();
    mockWorker = new Worker("1", "{}", "http://worker/api", "api-key");
    mockFlux = new SingleFluxDTO();
  }

  @Test
  void commit_shouldThrowCommitException_whenWorkerDoesNotExist() {
    // Arrange
    when(workerService.getWorkerByUid(validWorkerId)).thenReturn(null);

    // Act & Assert
    assertThrows(CommitException.class, () -> workerCommitService.commit(validWorkerId, validFluxId));

    // Verify
    verify(workerService, times(1)).getWorkerByUid(validWorkerId);
    verifyNoInteractions(fluxService);
  }

  @Test
  void commit_shouldThrowCommitException_whenFluxDoesNotExist() {
    // Arrange
    when(workerService.getWorkerByUid(validWorkerId)).thenReturn(mockWorker);
    when(fluxService.getFluxByUid(validFluxId)).thenReturn(null);

    // Act & Assert
    assertThrows(CommitException.class, () -> workerCommitService.commit(validWorkerId, validFluxId));

    // Verify
    verify(workerService, times(1)).getWorkerByUid(validWorkerId);
    verify(fluxService, times(1)).getFluxByUid(validFluxId);
  }

  @Test
  void commit_shouldSucceed_whenWorkerAndFluxExist() throws CommitException {
    // Arrange
    when(workerService.getWorkerByUid(validWorkerId)).thenReturn(mockWorker);
    when(fluxService.getFluxByUid(validFluxId)).thenReturn(mockFlux);

    // Act
    workerCommitService.commit(validWorkerId, validFluxId);
    when(workerRepository.save(any())).thenReturn(mockWorker);

    // Verify
    verify(workerService, times(1)).getWorkerByUid(validWorkerId);
    verify(fluxService, times(1)).getFluxByUid(validFluxId);

    // TODO :: Add additional verifications for any other behavior in the TODO section.
  }
}
