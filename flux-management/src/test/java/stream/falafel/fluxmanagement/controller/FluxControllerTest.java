package stream.falafel.fluxmanagement.controller;

import cpe.commons.api.flux.CreateFluxDTO;
import cpe.commons.api.flux.EditFluxDTO;
import cpe.commons.api.flux.FluxListSummary;
import cpe.commons.api.flux.SingleFluxDTO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stream.falafel.fluxmanagement.domain.EditFlux;
import stream.falafel.fluxmanagement.domain.Flux;
import stream.falafel.fluxmanagement.domain.FluxService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FluxControllerTest {

    @InjectMocks
    private FluxController fluxController;
    @Mock
    private FluxService fluxService;
    @Mock
    private FluxDTOMapper fluxDTOMapper;


    @Test
    void getByUidShouldReturnRightSingleFluxDTO() {
        // given
        Flux expectedFlux = new Flux("1", "name", "owner",
                "value", "ressources", new Date(11111));
        SingleFluxDTO expectedDTO = new SingleFluxDTO("1", "name", "owner",
                "value", "ressources", new Date(11111));

        //when
        when(fluxService.findByUid(expectedFlux.getUid())).thenReturn(expectedFlux);
        when(fluxDTOMapper.fluxToSingleFluxDTO(expectedFlux)).thenReturn(expectedDTO);

        ResponseEntity<SingleFluxDTO> response = fluxController.getFluxByUid(expectedFlux.getUid());

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedFlux.getUid(), response.getBody().getUid());
    }

    @Test
    void getByUidShouldReturn404WhenNotFound() {
        // given
        String nonExistentUid = "1";

        // when
        when(fluxService.findByUid(nonExistentUid)).thenReturn(null);

        ResponseEntity<SingleFluxDTO> response = fluxController.getFluxByUid(nonExistentUid);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findByOwnerShouldReturnRightListWhenFound() {
        //given
        Flux expectedFlux = new Flux("1", "name", "owner",
                "value", "ressources", new Date(11111));
        Flux expectedFlux2 = new Flux("2", "name", "owner",
                "value", "ressources", new Date(11111));

        FluxListSummary expectedFluxSummary = new FluxListSummary("1", "name", 11111);
        FluxListSummary expectedFlux2Summary = new FluxListSummary("2", "name", 11111);

        List<Flux> expectedFluxList = List.of(expectedFlux, expectedFlux2);
        List<FluxListSummary> expectedListDTO = List.of(expectedFluxSummary, expectedFlux2Summary);

        //when
        when(fluxService.findByOwner(expectedFlux.getOwner())).thenReturn(expectedFluxList);
        when(fluxDTOMapper.fluxToFluxListSummary(expectedFlux)).thenReturn(expectedFluxSummary);
        when(fluxDTOMapper.fluxToFluxListSummary(expectedFlux2)).thenReturn(expectedFlux2Summary);

        ResponseEntity<List<FluxListSummary>> response = fluxController.findByOwner(expectedFlux.getOwner());

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedListDTO, response.getBody());
    }

    @Test
    void findByOwnerShouldReturnEmptyListWhenNotFound() {
        // given
        String nonExistentOwner = "michel";
        // when
        when(fluxService.findByOwner(nonExistentOwner)).thenReturn(Collections.emptyList());

        ResponseEntity<List<FluxListSummary>> response = fluxController.findByOwner(nonExistentOwner);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void editFluxShouldReturnRightCodeAndSigleFluxDTOWhenUpdated() {
        // given
        Flux expectedFlux = new Flux("1", "name", "owner",
                "value", "ressources", new Date(11111));
        Flux expectedFluxModified = new Flux("1", "modifiedname", "owner",
                "value", "ressources", new Date(11111));
        SingleFluxDTO expectedDTO = new SingleFluxDTO("1", "modifiedname", "owner",
                "value", "ressources", new Date(11111));
        EditFluxDTO editFluxDTO = new EditFluxDTO("modifiedname", "value", "ressources");
        EditFlux editFlux = new EditFlux("modifiedname", "value", "ressources");

        //when
        when(fluxService.editFlux(expectedFlux.getUid(), editFlux)).thenReturn(expectedFluxModified);
        when(fluxDTOMapper.editFluxDTOToEditFlux(editFluxDTO)).thenReturn(editFlux);

        ResponseEntity<SingleFluxDTO> response = fluxController.editFlux(expectedFlux.getUid(), editFluxDTO);
        //then

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createFluxShouldReturnCreatedStatusAndSingleFluxDTO() {
        // given
        CreateFluxDTO createFluxDTO = new CreateFluxDTO("name", "owner");
        Flux flux = new Flux("1", "name", "owner", "value", "resources", new Date(1111));
        SingleFluxDTO singleFluxDTO = new SingleFluxDTO("1", "name", "owner", "value", "resources", new Date(1111));

        when(fluxDTOMapper.createFluxDTOToFlux(createFluxDTO)).thenReturn(flux);
        when(fluxService.createFlux(flux)).thenReturn(flux);
        when(fluxDTOMapper.fluxToSingleFluxDTO(flux)).thenReturn(singleFluxDTO);

        // when
        ResponseEntity<SingleFluxDTO> response = fluxController.createFlux(createFluxDTO);

        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(singleFluxDTO.getUid(), response.getBody().getUid());
    }

    @Test
    void deleteFluxShouldReturnNoContentWhenSuccessful() {
        // given
        String uid = "1";
        Flux flux = new Flux(uid, "name", "owner", "value", "resources", new Date(1111));
        SingleFluxDTO singleFluxDTO = new SingleFluxDTO(uid, "name", "owner", "value", "resources", new Date(1111));

        when(fluxService.findByUid(uid)).thenReturn(flux);
        when(fluxDTOMapper.fluxToSingleFluxDTO(flux)).thenReturn(singleFluxDTO);
        when(fluxService.deleteFlux(uid)).thenReturn(flux);

        // when
        ResponseEntity<SingleFluxDTO> response = fluxController.deleteFlux(uid);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteFluxShouldReturnNotFoundWhenFluxDoesNotExist() {
        // given
        String uid = "1";

        when(fluxService.findByUid(uid)).thenReturn(null);

        // when
        ResponseEntity<SingleFluxDTO> response = fluxController.deleteFlux(uid);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
