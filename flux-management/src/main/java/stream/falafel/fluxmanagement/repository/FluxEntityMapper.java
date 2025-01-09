package stream.falafel.fluxmanagement.repository;

import org.springframework.stereotype.Component;
import stream.falafel.fluxmanagement.domain.Flux;

import java.util.UUID;

@Component
public class FluxEntityMapper {

  public FluxEntity fluxToFluxEntity(Flux flux) {
    FluxEntity fluxEntity = new FluxEntity();
    fluxEntity.setName(flux.getName());
    fluxEntity.setOwner(flux.getOwner());
    fluxEntity.setValue(flux.getValue());
    fluxEntity.setLastEdited(flux.getLastEdited());
    fluxEntity.setRessourceDependencies(flux.getRessourceDependencies());
    return fluxEntity;
  }

  public FluxEntity fluxToFluxEntityToCreate(Flux flux) {
    FluxEntity fluxEntity = new FluxEntity();
    fluxEntity.setName(flux.getName());
    fluxEntity.setOwner(flux.getOwner());
    fluxEntity.setValue(flux.getValue());
    fluxEntity.setLastEdited(flux.getLastEdited());
    fluxEntity.setRessourceDependencies(flux.getRessourceDependencies());
    return fluxEntity;
  }

  public Flux fluxEntityToFlux(FluxEntity fluxEntity) {
    return new Flux(
        fluxEntity.getUid(),
        fluxEntity.getName(),
        fluxEntity.getOwner(),
        fluxEntity.getValue(),
        fluxEntity.getRessourceDependencies(),
        fluxEntity.getLastEdited());
  }
}
