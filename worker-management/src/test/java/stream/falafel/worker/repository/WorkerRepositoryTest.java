package stream.falafel.worker.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stream.falafel.worker.domain.worker.Worker;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkerRepositoryTest {

    @InjectMocks
    private WorkerRepository workerRepository;

    @Mock
    private WorkerEntityMapper workerEntityMapper;

    @Mock
    private WorkerJPARepository workerJPARepository;


    @Test
    void deleteByUidShouldInvokeDeleteMethod() {
        // given
        UUID uid = UUID.randomUUID();

        // when
        workerRepository.deleteByUid(uid);

        // then
        verify(workerJPARepository).deleteByUid(uid.toString());
    }

    @Test
    void findByUidShouldReturnEmptyIfNotFound() {
        UUID uid = UUID.randomUUID();

        when(workerJPARepository.findById(uid.toString())).thenReturn(Optional.empty());

        Optional<Worker> result = workerRepository.findByUid(uid);

        assertFalse(result.isPresent());
    }

}
