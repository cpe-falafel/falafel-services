package stream.falafel.fluxmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stream.falafel.fluxmanagement.domain.Flux;
import stream.falafel.fluxmanagement.domain.FluxService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/flux")
public class FluxController {

    private final FluxDTOMapper fluxDTOMapper;

    private final FluxService fluxService;

    @GetMapping("/")
    public SingleFluxDTO getFlux(@RequestParam String uid) {
        return fluxService.findByUid(uid);
    }

    @GetMapping
    public FluxListDTO findByOwner(@RequestParam String owner) {
        return fluxService.findByOwner(owner);
    }
}
