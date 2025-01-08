package stream.falafel.fluxmanagement.controller;

import cpe.commons.api.flux.CreateFluxDTO;
import cpe.commons.api.flux.EditFluxDTO;
import cpe.commons.api.flux.SingleFluxDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stream.falafel.fluxmanagement.domain.EditFlux;
import stream.falafel.fluxmanagement.domain.Flux;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FluxDTOMapperTest {

    @InjectMocks
    private FluxDTOMapper fluxDTOMapper;

    @Mock
    private Flux flux;

    @Mock
    EditFluxDTO editFluxDTO;

    @Mock
    CreateFluxDTO createFluxDTO;

    @Test
    void fluxToSingleFluxDTOShouldTransformFluxCorrectly() {
        // given
        Date lastEdited = new Date(11111);

        when(flux.getUid()).thenReturn("1");
        when(flux.getName()).thenReturn("name");
        when(flux.getOwner()).thenReturn("owner");
        when(flux.getValue()).thenReturn("value");
        when(flux.getRessourceDependencies()).thenReturn("resources");
        when(flux.getLastEdited()).thenReturn(lastEdited);

        // when
        SingleFluxDTO result = fluxDTOMapper.fluxToSingleFluxDTO(flux);

        // then
        assertNotNull(result);
        assertEquals("1", result.getUid());
        assertEquals("name", result.getName());
        assertEquals("owner", result.getOwner());
        assertEquals("value", result.getValue());
        assertEquals("resources", result.getRessourceDependencies());
        assertEquals(lastEdited, result.getLastEdited());
    }

    @Test
    void editFluxDTOToEditFluxShouldTransformCorrectly() {
        // given

        when(editFluxDTO.getName()).thenReturn("updatedName");
        when(editFluxDTO.getValue()).thenReturn("updatedValue");
        when(editFluxDTO.getRessourceDependencies()).thenReturn("updatedDependencies");

        // when
        EditFlux result = fluxDTOMapper.editFluxDTOToEditFlux(editFluxDTO);

        // then
        assertNotNull(result);
        assertEquals("updatedName", result.getName());
        assertEquals("updatedValue", result.getValue());
        assertEquals("updatedDependencies", result.getRessourceDependencies());
    }

    @Test
    void createFluxDTOToFluxShouldTransformCorrectly() {
        // given

        when(createFluxDTO.getName()).thenReturn("newFluxName");
        when(createFluxDTO.getOwner()).thenReturn("newOwner");

        // when
        Flux result = fluxDTOMapper.createFluxDTOToFlux(createFluxDTO);

        // then
        assertNotNull(result);
        assertNull(result.getUid());  // Assuming uid is not set on creation
        assertEquals("newFluxName", result.getName());
        assertEquals("newOwner", result.getOwner());
    }
}
