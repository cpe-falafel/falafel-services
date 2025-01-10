package stream.falafel.worker.domain.commit;

import cpe.commons.api.flux.SingleFluxDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stream.falafel.worker.domain.worker.Worker;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commit {
    private SingleFluxDTO flux;
    private Worker worker;
}
