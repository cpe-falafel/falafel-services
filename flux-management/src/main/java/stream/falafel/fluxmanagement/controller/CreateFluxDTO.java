package stream.falafel.fluxmanagement.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateFluxDTO { //Used when creating a new flux
    //TestService testService = null;
    //Testdto dto = null;

    private String owner;

    private String name;
}
