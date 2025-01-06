package stream.falafel.fluxmanagement.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FluxListSummary {
    private String uid;
    private String name;
    private long lastEdited;
}
