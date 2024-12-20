package stream.falafel.fluxmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flux {
    private String uid;

    private String name;

    private String owner;

    private String value; //json with configuration => Destined to be an object

    private String ressourceDependencies;

    private Date lastEdited;


}
