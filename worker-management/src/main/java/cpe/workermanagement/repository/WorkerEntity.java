package cpe.workermanagement.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;

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

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @Column(name = "configuration_value", nullable = false)
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