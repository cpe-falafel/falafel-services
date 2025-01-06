package stream.falafel.fluxmanagement.controller;

import org.springframework.web.bind.annotation.*;
import stream.falafel.fluxmanagement.domain.FluxService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/flux")
public class FluxController {

    private final FluxDTOMapper fluxDTOMapper;

    private final FluxService fluxService;

    @GetMapping("/{uid}")
    public SingleFluxDTO getFlux(@PathVariable String uid) {
        return fluxDTOMapper.fluxToSingleFluxDTO(fluxService.findByUid(uid));
    }

    @GetMapping
    public List<FluxListSummary> findByOwner(@RequestParam String group) {
        return fluxService.findByOwner(group).stream().map(fluxDTOMapper::fluxToFluxListSummary).toList();
    }

    @PutMapping("/{uid}")
    public void editFlux(@PathVariable String uid, @RequestParam EditFluxDTO editFluxDTO) {
        fluxService.editFlux(uid, fluxDTOMapper.editFluxDTOToEditFlux(editFluxDTO));
    }

    @PostMapping
    public void createFlux(@RequestParam CreateFluxDTO createFluxDTO) {
        fluxService.createFlux(fluxDTOMapper.createFluxDTOToFlux(createFluxDTO));
    }
}
