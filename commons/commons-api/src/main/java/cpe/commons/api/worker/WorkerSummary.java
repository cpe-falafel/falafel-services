package cpe.commons.api.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerSummary {
    private String uid;
    private long lastUpdate;
    private String uri;
}
