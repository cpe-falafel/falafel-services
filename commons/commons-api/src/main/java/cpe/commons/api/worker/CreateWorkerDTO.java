package cpe.commons.api.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkerDTO {
    private String group;
    private String uri;
    private String lastFluxUid;
    private String configurationValue;
    private String apiKey;
}
