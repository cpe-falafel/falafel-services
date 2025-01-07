package cpe.commons.api.flux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditFluxDTO {

    private String name;

    private String value;

    private String ressourceDependencies;
}
