package stream.falafel.worker.domain;

import cpe.commons.api.flux.SingleFluxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stream.falafel.worker.domain.commit.CommitService;
import stream.falafel.worker.domain.flux.FluxService;
import stream.falafel.worker.domain.worker.Worker;
import stream.falafel.worker.domain.worker.WorkerService;
import stream.falafel.worker.exception.CommitException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CommitServiceTest {

  @Mock private WorkerService workerService;

  @Mock private FluxService fluxService;

  @InjectMocks private CommitService commitService;

  private UUID validWorkerId;
  private UUID validFluxId;
  private Worker mockWorker;
  private SingleFluxDTO mockFlux;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    validWorkerId = UUID.randomUUID();
    validFluxId = UUID.randomUUID();
    mockWorker = new Worker("{}", "http://worker/api", "api-key");
    mockFlux = new SingleFluxDTO();
  }

  @Test
  void commit_shouldThrowCommitException_whenWorkerDoesNotExist() {
    // Arrange
    when(workerService.getWorkerByUid(validWorkerId)).thenReturn(null);

    // Act & Assert
    assertThrows(CommitException.class, () -> commitService.commit(validWorkerId, validFluxId));

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
    assertThrows(CommitException.class, () -> commitService.commit(validWorkerId, validFluxId));

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
    commitService.commit(validWorkerId, validFluxId);

    // Verify
    verify(workerService, times(1)).getWorkerByUid(validWorkerId);
    verify(fluxService, times(1)).getFluxByUid(validFluxId);

    // TODO :: Add additional verifications for any other behavior in the TODO section.
  }
}
