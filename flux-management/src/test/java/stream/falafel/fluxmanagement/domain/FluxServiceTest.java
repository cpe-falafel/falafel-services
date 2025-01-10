package stream.falafel.fluxmanagement.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FluxServiceTest {

  @InjectMocks private FluxService fluxService;

  @Mock private IFluxRepository iFluxRepository;

  @Test
  void findByUidShouldReturnFluxWhenExists() {
    // given
    String uid = "1";
    Flux expectedFlux = new Flux(uid, "name", "owner", "value", "resources", new Date(1111));
    when(iFluxRepository.findByUid(uid)).thenReturn(expectedFlux);

    // when
    Flux result = fluxService.findByUid(uid);

    // then
    assertNotNull(result);
    assertEquals(uid, result.getUid());
    verify(iFluxRepository).findByUid(uid);
  }

  @Test
  void findByOwnerShouldReturnListOfFluxWhenExists() {
    // given
    String owner = "owner";
    List<Flux> expectedFluxList =
        List.of(
            new Flux("1", "name1", owner, "value1", "resources1", new Date(1111)),
            new Flux("2", "name2", owner, "value2", "resources2", new Date(1111)));
    when(iFluxRepository.findByOwner(owner)).thenReturn(expectedFluxList);

    // when
    List<Flux> result = fluxService.findByOwner(owner);

    // then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(iFluxRepository).findByOwner(owner);
  }

  @Test
  void editFluxShouldUpdateFluxAndReturnUpdatedObject() {
    // given
    String uid = "1";
    EditFlux editFlux = new EditFlux("newName", "newValue", "newDependencies");
    Flux existingFlux = new Flux(uid, "name", "owner", "value", "resources", new Date(1111));

    when(iFluxRepository.findByUid(uid)).thenReturn(existingFlux);
    doNothing().when(iFluxRepository).editFlux(existingFlux);

    // when
    Flux result = fluxService.editFlux(uid, editFlux);

    // then
    assertNotNull(result);
    assertEquals(editFlux.getName(), result.getName());
    assertEquals(editFlux.getValue(), result.getValue());
    assertEquals(editFlux.getRessourceDependencies(), result.getRessourceDependencies());
    verify(iFluxRepository).findByUid(uid);
    verify(iFluxRepository).editFlux(existingFlux);
  }

  @Test
  void createFluxShouldSaveAndReturnFlux() {
    // given
    Flux flux = new Flux("1", "name", "owner", "value", "resources", new Date(1111));
    when(iFluxRepository.createFlux(flux)).thenReturn(flux);

    // when
    Flux result = fluxService.createFlux(flux);

    // then
    assertNotNull(result);
    assertEquals(flux.getUid(), result.getUid());
    verify(iFluxRepository).createFlux(flux);
  }

  @Test
  void deleteFluxShouldRemoveAndReturnFlux() {
    // given
    String uid = "1";
    Flux existingFlux = new Flux(uid, "name", "owner", "value", "resources", new Date(1111));
    when(iFluxRepository.findByUid(uid)).thenReturn(existingFlux);
    doNothing().when(iFluxRepository).deleteFluxByUid(uid);

    // when
    Flux result = fluxService.deleteFlux(uid);

    // then
    assertNotNull(result);
    assertEquals(uid, result.getUid());
    verify(iFluxRepository).findByUid(uid);
    verify(iFluxRepository).deleteFluxByUid(uid);
  }
}
