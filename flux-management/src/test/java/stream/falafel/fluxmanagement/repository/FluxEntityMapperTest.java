package stream.falafel.fluxmanagement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stream.falafel.fluxmanagement.domain.Flux;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FluxEntityMapperTest {

    @InjectMocks
    private FluxEntityMapper fluxEntityMapper;

    @Mock
    private FluxEntity fluxEntity;

    @Mock
    private Flux flux;

    @Test
    void fluxToFluxEntityShouldMapFluxToFluxEntity() {
        // given
        when(flux.getName()).thenReturn("name");
        when(flux.getOwner()).thenReturn("owner");
        when(flux.getValue()).thenReturn("value");
        when(flux.getLastEdited()).thenReturn(new Date(11111));
        when(flux.getRessourceDependencies()).thenReturn("resources");

        // when
        FluxEntity result = fluxEntityMapper.fluxToFluxEntity(flux);

        // then
        assertNotNull(result);
        assertEquals("name", result.getName());
        assertEquals("owner", result.getOwner());
        assertEquals("value", result.getValue());
        assertEquals(new Date(11111), result.getLastEdited());
        assertEquals("resources", result.getRessourceDependencies());
    }

    @Test
    void fluxToFluxEntityToCreateShouldGenerateUidAndMapFields() {
        // given
        when(flux.getName()).thenReturn("name");
        when(flux.getOwner()).thenReturn("owner");
        when(flux.getValue()).thenReturn("value");
        when(flux.getLastEdited()).thenReturn(new Date(11111));
        when(flux.getRessourceDependencies()).thenReturn("resources");

        // when
        FluxEntity result = fluxEntityMapper.fluxToFluxEntityToCreate(flux);

        // then
        assertNotNull(result);
        assertNotNull(result.getUid());  // Ensure UUID is generated
        assertEquals("name", result.getName());
        assertEquals("owner", result.getOwner());
        assertEquals("value", result.getValue());
        assertEquals(new Date(11111), result.getLastEdited());
        assertEquals("resources", result.getRessourceDependencies());
    }

    @Test
    void fluxEntityToFluxShouldMapFluxEntityToFlux() {
        // given
        when(fluxEntity.getUid()).thenReturn("123");
        when(fluxEntity.getName()).thenReturn("name");
        when(fluxEntity.getOwner()).thenReturn("owner");
        when(fluxEntity.getValue()).thenReturn("value");
        when(fluxEntity.getLastEdited()).thenReturn(new Date(11111));
        when(fluxEntity.getRessourceDependencies()).thenReturn("resources");

        // when
        Flux result = fluxEntityMapper.fluxEntityToFlux(fluxEntity);

        // then
        assertNotNull(result);
        assertEquals("123", result.getUid());
        assertEquals("name", result.getName());
        assertEquals("owner", result.getOwner());
        assertEquals("value", result.getValue());
        assertEquals(new Date(11111), result.getLastEdited());
        assertEquals("resources", result.getRessourceDependencies());
    }
}
