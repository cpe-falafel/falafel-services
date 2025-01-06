package cpe.commons.api.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleWorkerDTO {
    private String uid;
    private String owner;
    private long lastUpdate;
    private String configurationValue;
    private String previewUri;
    private String previewKey;
}
