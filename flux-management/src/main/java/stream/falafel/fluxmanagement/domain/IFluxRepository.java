package stream.falafel.fluxmanagement.domain;

import java.util.List;

public interface IFluxRepository {
  Flux findByUid(String uid);

  List<Flux> findByOwner(String owner);

  void editFlux(Flux flux);

  Flux createFlux(Flux flux);

  void deleteFluxByUid(String uid);
}
