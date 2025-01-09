package stream.falafel.fluxmanagement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stream.falafel.fluxmanagement.domain.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FluxRepositoryTest {

    @InjectMocks
    private FluxRepository fluxRepository;

    @Mock
    private FluxJPARepository fluxJPARepository;

    @Mock
    private FluxEntityMapper fluxEntityMapper;


    @Test
    void findByUidShouldReturnFlux() {
        // given
        String uid = "123";
        FluxEntity fluxEntity = new FluxEntity();
        Flux flux = new Flux();

        when(fluxJPARepository.findByUid(uid)).thenReturn(fluxEntity);
        when(fluxEntityMapper.fluxEntityToFlux(fluxEntity)).thenReturn(flux);

        // when
        Flux result = fluxRepository.findByUid(uid);

        // then
        assertNotNull(result);
        assertEquals(flux, result);
        verify(fluxJPARepository).findByUid(uid);
        verify(fluxEntityMapper).fluxEntityToFlux(fluxEntity);
    }

    @Test
    void findByOwnerShouldReturnListOfFlux() {
        // given
        String owner = "owner1";
        List<FluxEntity> fluxEntities = List.of(new FluxEntity(), new FluxEntity());
        List<Flux> expectedFluxList = List.of(new Flux(), new Flux());

        when(fluxJPARepository.findByOwner(owner)).thenReturn(fluxEntities);
        when(fluxEntityMapper.fluxEntityToFlux(fluxEntities.get(0))).thenReturn(expectedFluxList.get(0));
        when(fluxEntityMapper.fluxEntityToFlux(fluxEntities.get(1))).thenReturn(expectedFluxList.get(1));

        // when
        List<Flux> result = fluxRepository.findByOwner(owner);

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedFluxList, result);
        verify(fluxJPARepository).findByOwner(owner);
    }

    @Test
    void editFluxShouldSaveMappedFluxEntity() {
        // given
        Flux flux = new Flux();
        FluxEntity fluxEntity = new FluxEntity();

        when(fluxEntityMapper.fluxToFluxEntity(flux)).thenReturn(fluxEntity);

        // when
        fluxRepository.editFlux(flux);

        // then
        verify(fluxEntityMapper).fluxToFluxEntity(flux);
        verify(fluxJPARepository).save(fluxEntity);
    }

    @Test
    void createFluxShouldSaveNewFluxEntity() {
        // given
        Flux flux = new Flux();
        FluxEntity fluxEntity = new FluxEntity();

        when(fluxEntityMapper.fluxToFluxEntityToCreate(flux)).thenReturn(fluxEntity);

        // when
        fluxRepository.createFlux(flux);

        // then
        verify(fluxEntityMapper).fluxToFluxEntityToCreate(flux);
        verify(fluxJPARepository).save(fluxEntity);
    }

    @Test
    void deleteFluxByUidShouldRemoveEntity() {
        // given
        String uid = "123";
        FluxEntity fluxEntity = new FluxEntity();

        when(fluxJPARepository.findByUid(uid)).thenReturn(fluxEntity);

        // when
        fluxRepository.deleteFluxByUid(uid);

        // then
        verify(fluxJPARepository).findByUid(uid);
        verify(fluxJPARepository).delete(fluxEntity);
    }

}
