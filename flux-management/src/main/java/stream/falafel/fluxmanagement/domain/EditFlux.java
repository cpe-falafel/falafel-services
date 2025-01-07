package stream.falafel.fluxmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditFlux {
    private String name;

    private String value;

    private String ressourceDependencies;
}
