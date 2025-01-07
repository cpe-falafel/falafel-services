package stream.falafel.fluxmanagement.controller;

import cpe.commons.api.flux.CreateFluxDTO;
import cpe.commons.api.flux.EditFluxDTO;
import cpe.commons.api.flux.FluxListSummary;
import cpe.commons.api.flux.SingleFluxDTO;
import org.springframework.stereotype.Component;
import stream.falafel.fluxmanagement.domain.EditFlux;
import stream.falafel.fluxmanagement.domain.Flux;

@Component
public class FluxDTOMapper {

    public SingleFluxDTO fluxToSingleFluxDTO(Flux flux){
       return new SingleFluxDTO(flux.getUid(), flux.getName(), flux.getOwner(),
               flux.getValue(), flux.getRessourceDependencies(), flux.getLastEdited());
    }

    public EditFlux editFluxDTOToEditFlux(EditFluxDTO editFluxDTO){
        return new EditFlux(editFluxDTO.getName(), editFluxDTO.getValue(), editFluxDTO.getRessourceDependencies());
    }

    public Flux createFluxDTOToFlux(CreateFluxDTO createFluxDTO){
        return new Flux(null, createFluxDTO.getName(), createFluxDTO.getOwner(), null,null,null);
    }

    public FluxListSummary fluxToFluxListSummary(Flux flux){
        FluxListSummary fluxListSummary = new FluxListSummary();
        fluxListSummary.setUid(flux.getUid());
        fluxListSummary.setName(flux.getName());
        fluxListSummary.setLastEdited(flux.getLastEdited().getTime());
        return fluxListSummary;
    }
}
