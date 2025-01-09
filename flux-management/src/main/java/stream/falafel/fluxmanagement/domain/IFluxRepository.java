package stream.falafel.fluxmanagement.domain;

import java.util.List;

public interface IFluxRepository {
     Flux findByUid(String uid);

     List<Flux> findByOwner(String owner);

     void editFlux(Flux flux);

     void createFlux(Flux flux);

     void deleteFluxByUid(String uid);
}
