package stream.falafel.service;

import cpe.commons.api.flux.CreateFluxDTO;
import cpe.commons.api.flux.EditFluxDTO;
import cpe.commons.api.flux.FluxListSummary;
import cpe.commons.api.flux.SingleFluxDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FluxHttpClientTest {

    @InjectMocks
    private FluxHttpClient fluxHttpClient;

    @Mock
    RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8080/api";

    /*
    @Test
    void getFluxByUidShouldReturnSingleFluxDTO() {
        UUID fluxId = UUID.randomUUID();
        String url = baseUrl + "/flux/" + fluxId;
        SingleFluxDTO expectedResponse = new SingleFluxDTO(fluxId.toString(),"testName", "testOwner", "testValue","testRessources",new Date(1111));


        when(restTemplate.getForEntity(url, SingleFluxDTO.class))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        SingleFluxDTO result = fluxHttpClient.getFluxByUid(fluxId);

        assertNotNull(result);
        assertEquals("testName", result.getName());
        verify(restTemplate).getForEntity(eq(url), eq(SingleFluxDTO.class));
    }


    @Test
    void findFluxByOwnerShouldReturnListOfFluxListSummary() {
        String group = "testGroup";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("group", group).toUriString();
        FluxListSummary[] expectedResponse = {
                new FluxListSummary("uid1","name1",111),
                new FluxListSummary("uid2","name2",222),
        };

        when(restTemplate.getForEntity(url, FluxListSummary[].class))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        List<FluxListSummary> result = fluxHttpClient.findFluxByOwner(group);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("flux1", result.get(0).getName());
        verify(restTemplate).getForEntity(url, FluxListSummary[].class);
    }

    @Test
    void createFluxShouldInvokeRestTemplatePost() {
        CreateFluxDTO createFluxDTO = new CreateFluxDTO("owner", "name");
        String url = baseUrl + "/flux";

        when(restTemplate.postForEntity(eq(url), eq(createFluxDTO), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        fluxHttpClient.createFlux(createFluxDTO);

        verify(restTemplate).postForEntity(eq(url), eq(createFluxDTO), eq(Void.class));
    }

    @Test
    void editFluxShouldInvokeRestTemplatePut() {
        String uid = "123";
        EditFluxDTO editFluxDTO = new EditFluxDTO("newName", "newValue", "ressources");
        String url =  baseUrl + "/flux/" + uid;

        fluxHttpClient.editFlux(uid, editFluxDTO);

        verify(restTemplate).put(eq(url), eq(editFluxDTO));
    }
     */
}
