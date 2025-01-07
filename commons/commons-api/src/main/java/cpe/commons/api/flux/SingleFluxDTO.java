package cpe.commons.api.flux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleFluxDTO { //Only used when retrieve user from front
    private String uid;

    private String name;

    private String owner;

    private String value; //json with configuration => Destined to be an object

    private String ressourceDependencies;

    private Date lastEdited;
}
