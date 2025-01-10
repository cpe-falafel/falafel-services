package stream.falafel.worker.controller;

import cpe.commons.api.worker.CreateWorkerDTO;
import cpe.commons.api.worker.SingleWorkerDTO;
import cpe.commons.api.worker.WorkerListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import stream.falafel.worker.domain.commit.WorkerCommitService;
import stream.falafel.worker.domain.worker.Worker;
import stream.falafel.worker.domain.worker.WorkerService;
import stream.falafel.worker.exception.CommitException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class WorkerControllerTest {

    private WorkerService workerService;
    private WorkerCommitService workerCommitService;
    private WorkerController workerController;

    @BeforeEach
    void setup() {
        workerService = mock(WorkerService.class);
        workerCommitService = mock(WorkerCommitService.class);
        workerController = new WorkerController(workerService, workerCommitService);
    }

    @Test
    void testGetAllWorkers_ReturnsEmptyList() {
        // Arrange
        String group = "test-group";
        Mockito.when(workerService.getAllWorkers(group)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<WorkerListDTO> response = workerController.getAllWorkers(group);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testGetAllWorkers_ReturnsWorkers() {
        // Arrange
        String group = "test-group";
        Worker worker = new Worker("1", "{}", "http://worker-node/api", "api-key");
        List<Worker> workers = List.of(worker);

        Mockito.when(workerService.getAllWorkers(group)).thenReturn(workers);

        // Act
        ResponseEntity<WorkerListDTO> response = workerController.getAllWorkers(group);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getValues().size());
    }

    @Test
    void testGetWorkerById_ReturnsWorker() {
        // Arrange
        Worker worker = new Worker("1","{}", "http://worker-node/api", "api-key");
        Mockito.when(workerService.getWorkerByUid(worker.getUid())).thenReturn(worker);

        // Act
        ResponseEntity<SingleWorkerDTO> response = workerController.getWorkerById(worker.getUid());

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(worker.getUid(), response.getBody().getUid());
    }

    @Test
    void testGetWorkerById_NotFound() {
        // Arrange
        UUID workerId = UUID.randomUUID();
        Mockito.when(workerService.getWorkerByUid(workerId)).thenReturn(null);

        // Act
        ResponseEntity<SingleWorkerDTO> response = workerController.getWorkerById(workerId);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateWorker_Success() {
        // Arrange
        CreateWorkerDTO createWorkerDTO = new CreateWorkerDTO("groupUuid","http://worker-node/api","1", "{}", "api-key");
        Worker worker = new Worker("1", "{}", "http://worker-node/api", "api-key");

        Mockito.when(workerService.createWorker(Mockito.any())).thenReturn(worker);

        // Act
        ResponseEntity<SingleWorkerDTO> response = workerController.createWorker(createWorkerDTO);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(worker.getUid(), response.getBody().getUid());
    }

    @Test
    void testDeleteWorker_Success() {
        // Arrange
        UUID workerId = UUID.randomUUID();
        Worker worker = new Worker("1", "{}", "http://worker-node/api", "api-key");
        Mockito.when(workerService.getWorkerByUid(workerId)).thenReturn(worker);

        // Act
        ResponseEntity<Void> response = workerController.deleteWorker(workerId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        Mockito.verify(workerService, Mockito.times(1)).deleteWorker(workerId);
    }

    @Test
    void testDeleteWorker_NotFound() {
        // Arrange
        UUID workerId = UUID.randomUUID();
        Mockito.when(workerService.getWorkerByUid(workerId)).thenReturn(null);

        // Act
        ResponseEntity<Void> response = workerController.deleteWorker(workerId);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCommitFlux_Success() throws CommitException {
        // Arrange
        UUID workerUuid = UUID.randomUUID();
        UUID fluxUuid = UUID.randomUUID();

        // No exceptions from commitService.commit()
        Mockito.doNothing().when(workerCommitService).commit(workerUuid, fluxUuid);

        // Act
        ResponseEntity<Void> response = workerController.commitFlux(workerUuid, fluxUuid);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        Mockito.verify(workerCommitService, Mockito.times(1)).commit(workerUuid, fluxUuid);
    }

    @Test
    void testCommitFlux_ThrowsCommitException() throws CommitException {
        // Arrange
        UUID workerId = UUID.randomUUID();
        UUID fluxId = UUID.randomUUID();

        Mockito.doThrow(new CommitException("Test exception")).when(workerCommitService).commit(workerId, fluxId);

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(CommitException.class, () -> {
            workerController.commitFlux(workerId, fluxId);
        });
    }
}

