package stream.falafel.fluxmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FluxService {

  private final IFluxRepository iFluxRepository;

  public FluxService(IFluxRepository iFluxRepository) {
    this.iFluxRepository = iFluxRepository;
  }

  public Flux findByUid(String uid) {
    return iFluxRepository.findByUid(uid);
  }

  public List<Flux> findByOwner(String owner) {
    return iFluxRepository.findByOwner(owner);
  }

  public Flux editFlux(String uid, EditFlux editFlux) {
    Flux f = findByUid(uid);
    f.setName(editFlux.getName());
    f.setValue(editFlux.getValue());
    f.setRessourceDependencies(editFlux.getRessourceDependencies());
    iFluxRepository.editFlux(f);
    return f;
  }

  public Flux createFlux(Flux f) {
    return iFluxRepository.createFlux(f);
  }

  public Flux deleteFlux(String uid) {
    Flux f = iFluxRepository.findByUid(uid);
    iFluxRepository.deleteFluxByUid(uid);
    return f;
  }
}
