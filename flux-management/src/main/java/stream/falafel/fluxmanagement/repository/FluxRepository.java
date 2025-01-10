package stream.falafel.fluxmanagement.repository;

import org.springframework.stereotype.Repository;
import stream.falafel.fluxmanagement.domain.Flux;
import stream.falafel.fluxmanagement.domain.IFluxRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Repository
public class FluxRepository implements IFluxRepository {

  private final FluxEntityMapper fluxEntityMapper;

  private final FluxJPARepository fluxJPARepository;

  @Override
  public Flux findByUid(String uid) {
    return fluxEntityMapper.fluxEntityToFlux(fluxJPARepository.findByUid(uid));
  }

  @Override
  public List<Flux> findByOwner(String owner) {
    return fluxJPARepository.findByOwner(owner).stream()
        .map(fluxEntityMapper::fluxEntityToFlux)
        .toList();
  }

  @Override
  public void editFlux(Flux flux) {
    fluxJPARepository.save(fluxEntityMapper.fluxToFluxEntity(flux));
  }

  @Override
  public Flux createFlux(Flux flux) {
    FluxEntity entity = fluxJPARepository.save(fluxEntityMapper.fluxToFluxEntityToCreate(flux));
    return fluxEntityMapper.fluxEntityToFlux(entity);
  }

  @Override
  public void deleteFluxByUid(String uid) {
    FluxEntity fluxToDelete = fluxJPARepository.findByUid(uid);
    fluxJPARepository.delete(fluxToDelete);
  }
}
