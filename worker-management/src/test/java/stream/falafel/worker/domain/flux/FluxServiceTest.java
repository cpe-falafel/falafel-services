package stream.falafel.worker.domain.flux;

import cpe.commons.api.flux.SingleFluxDTO;
import stream.falafel.service.FluxHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FluxServiceTest {

  @Mock private FluxHttpClient fluxHttpClient;

  @InjectMocks private FluxService fluxService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetFluxByUid() {
    // Arrange
    UUID uuid = UUID.randomUUID();
    SingleFluxDTO expectedFluxDTO = new SingleFluxDTO();
    when(fluxHttpClient.getFluxByUid(uuid)).thenReturn(expectedFluxDTO);

    // Act
    SingleFluxDTO result = fluxService.getFluxByUid(uuid);

    // Assert
    assertNotNull(result, "The result should not be null");
    assertEquals(expectedFluxDTO, result, "The returned FluxDTO should match the expected one");
    verify(fluxHttpClient, times(1)).getFluxByUid(uuid);
  }
}
