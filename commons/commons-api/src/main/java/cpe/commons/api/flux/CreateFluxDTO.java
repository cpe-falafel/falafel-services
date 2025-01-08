package cpe.commons.api.flux;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateFluxDTO {

    private String owner;

    private String name;
}
