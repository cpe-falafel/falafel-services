package stream.falafel.worker.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "worker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerEntity {

    @Id
    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "group_uid")
    private String groupUid;

    @Column(name = "last_flux_uid")
    private String lastFluxUid;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @Column(name = "configuration_value", nullable = false, columnDefinition = "TEXT")
    private String configurationValue;

    @Column(name = "uri", nullable = false)
    private String uri;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "preview_uri")
    private String previewUri;

    @Column(name = "preview_key")
    private String previewKey;

    @Column(name = "package_key")
    private String packageKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerEntity that = (WorkerEntity) o;
        return uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }
}