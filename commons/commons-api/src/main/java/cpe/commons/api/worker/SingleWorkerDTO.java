package cpe.commons.api.worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleWorkerDTO {
  private UUID uid;
  private UUID group;
  private long lastUpdate;
  private String configurationValue;
  private String previewUri;
  private String previewKey;
}
