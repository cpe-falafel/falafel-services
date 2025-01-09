package stream.falafel.worker.domain.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Worker {
    private UUID uid;
    private UUID groupUid;
    private LocalDate lastUpdate;
    private String configurationValue;
    private String uri;
    private String apiKey;
    private String previewUri;
    private String previewKey;
    private String packageKey;

    public Worker(@NonNull String configurationValue, @NonNull String uri, String apiKey) {
        this.uid = UUID.randomUUID();
        this.lastUpdate = LocalDate.now();
        this.configurationValue = configurationValue;
        this.uri = uri;
        this.apiKey = apiKey;
    }

    public Worker(UUID groupUid, @NonNull String configurationValue, @NonNull String uri, String apiKey) {
        this.uid = UUID.randomUUID();
        this.groupUid = groupUid;
        this.lastUpdate = LocalDate.now();
        this.configurationValue = configurationValue;
        this.uri = uri;
        this.apiKey = apiKey;
    }
}