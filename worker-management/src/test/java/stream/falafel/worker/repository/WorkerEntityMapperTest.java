package stream.falafel.worker.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stream.falafel.worker.domain.worker.Worker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkerEntityMapperTest {

  @InjectMocks private WorkerEntityMapper workerEntityMapper;

  @Mock private WorkerEntity workerEntity;

  @Mock private Worker worker;

  @Test
  void toDomainShouldReturnWorkerWhenEntityIsNotNull() {
    UUID uid = UUID.randomUUID();
    UUID groupUid = UUID.randomUUID();
    LocalDateTime lastUpdate = LocalDateTime.now();

    when(workerEntity.getUid()).thenReturn(uid.toString());
    when(workerEntity.getGroupUid()).thenReturn(groupUid.toString());
    when(workerEntity.getLastUpdate()).thenReturn(LocalDate.from(lastUpdate));
    when(workerEntity.getConfigurationValue()).thenReturn("config-value");
    when(workerEntity.getUri()).thenReturn("http://example.com/worker");
    when(workerEntity.getApiKey()).thenReturn("api-key");
    when(workerEntity.getPreviewUri()).thenReturn("http://example.com/preview");
    when(workerEntity.getPreviewKey()).thenReturn("preview-key");
    when(workerEntity.getPackageKey()).thenReturn("package-key");

    Worker result = workerEntityMapper.toDomain(workerEntity);

    assertNotNull(result);
    assertEquals(uid, result.getUid());
    assertEquals(groupUid, result.getGroupUid());
    assertEquals("config-value", result.getConfigurationValue());
    assertEquals("http://example.com/worker", result.getUri());
    assertEquals("api-key", result.getApiKey());
    assertEquals("http://example.com/preview", result.getPreviewUri());
    assertEquals("preview-key", result.getPreviewKey());
    assertEquals("package-key", result.getPackageKey());
  }

  @Test
  void toDomainShouldReturnNullWhenEntityIsNull() {
    Worker result = workerEntityMapper.toDomain(null);
    assertNull(result);
  }

  @Test
  void toEntityShouldReturnWorkerEntityWhenDomainIsNotNull() {
    UUID uid = UUID.randomUUID();
    String groupUid = "null";
    LocalDateTime lastUpdate = LocalDateTime.now();

    when(worker.getUid()).thenReturn(uid);
    when(worker.getGroupUid()).thenReturn(groupUid);
    when(worker.getLastUpdate()).thenReturn(LocalDate.from(lastUpdate));
    when(worker.getConfigurationValue()).thenReturn("config-value");
    when(worker.getUri()).thenReturn("http://example.com/worker");
    when(worker.getApiKey()).thenReturn("api-key");
    when(worker.getPreviewUri()).thenReturn("http://example.com/preview");
    when(worker.getPreviewKey()).thenReturn("preview-key");
    when(worker.getPackageKey()).thenReturn("package-key");

    WorkerEntity result = workerEntityMapper.toEntity(worker);

    assertNotNull(result);
    assertEquals(uid.toString(), result.getUid());
    assertEquals(groupUid.toString(), result.getGroupUid());
    assertEquals("config-value", result.getConfigurationValue());
    assertEquals("http://example.com/worker", result.getUri());
    assertEquals("api-key", result.getApiKey());
    assertEquals("http://example.com/preview", result.getPreviewUri());
    assertEquals("preview-key", result.getPreviewKey());
    assertEquals("package-key", result.getPackageKey());
  }

  @Test
  void toEntityShouldReturnNullWhenDomainIsNull() {
    WorkerEntity result = workerEntityMapper.toEntity(null);
    assertNull(result);
  }
}
