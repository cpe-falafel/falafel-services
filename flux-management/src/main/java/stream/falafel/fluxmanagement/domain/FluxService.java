package stream.falafel.fluxmanagement.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FluxService {

    private final IFluxRepository iFluxRepository;

    public Flux findByUid(String uid) {
        return iFluxRepository.findByUid(uid);
    }

    public List<Flux> findByOwner(String owner) {
        return iFluxRepository.findByOwner(owner);
    }

    public void editFlux(String uid, EditFlux editFlux) {
        Flux f = findByUid(uid);
        f.setName(editFlux.getName());
        f.setValue(editFlux.getValue());
        f.setRessourceDependencies(editFlux.getRessourceDependencies());
        iFluxRepository.editFlux(f);
    }

    public void createFlux(Flux f) {
        iFluxRepository.createFlux(f);
    }
}
