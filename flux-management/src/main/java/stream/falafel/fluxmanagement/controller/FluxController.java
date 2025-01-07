package stream.falafel.fluxmanagement.controller;

import cpe.commons.api.flux.CreateFluxDTO;
import cpe.commons.api.flux.EditFluxDTO;
import cpe.commons.api.flux.FluxListSummary;
import cpe.commons.api.flux.SingleFluxDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stream.falafel.fluxmanagement.domain.FluxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/flux")
public class FluxController {

    private final FluxDTOMapper fluxDTOMapper;

    private final FluxService fluxService;

    @GetMapping("/{uid}")
    public ResponseEntity<SingleFluxDTO> getFluxByUid(@PathVariable("uid") String uid) {
        SingleFluxDTO f = fluxDTOMapper.fluxToSingleFluxDTO(fluxService.findByUid(uid));
        if (f != null)
            return ResponseEntity.ok(f);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<FluxListSummary>> findByOwner(@RequestParam String group) {
        List<FluxListSummary> list = fluxService.findByOwner(group).stream()
                .map(fluxDTOMapper::fluxToFluxListSummary).toList();
        if (!list.isEmpty())
            return ResponseEntity.ok(list);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity<SingleFluxDTO> editFlux(@PathVariable("uid") String uid, @RequestBody EditFluxDTO editFluxDTO) {
        SingleFluxDTO f = fluxDTOMapper.fluxToSingleFluxDTO(
                fluxService.editFlux(uid, fluxDTOMapper.editFluxDTOToEditFlux(editFluxDTO)));
        return ResponseEntity.ok(f);
    }

    @PostMapping
    public ResponseEntity<SingleFluxDTO> createFlux(@RequestBody CreateFluxDTO createFluxDTO) {
        SingleFluxDTO f = fluxDTOMapper.fluxToSingleFluxDTO(
                fluxService.createFlux(fluxDTOMapper.createFluxDTOToFlux(createFluxDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(f);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<SingleFluxDTO> deleteFlux(@PathVariable("uid") String uid) {
        SingleFluxDTO existingFlux = fluxDTOMapper.fluxToSingleFluxDTO(fluxService.findByUid(uid));
        if (existingFlux == null) {
            return ResponseEntity.notFound().build();
        }
        SingleFluxDTO f = fluxDTOMapper.fluxToSingleFluxDTO(fluxService.deleteFlux(uid));
        return ResponseEntity.noContent().build();
    }
}
