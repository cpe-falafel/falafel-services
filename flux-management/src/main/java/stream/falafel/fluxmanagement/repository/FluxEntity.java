package stream.falafel.fluxmanagement.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity(name = "flux")
public class FluxEntity {
  @Id private String uid;

  private String name;

  private String owner;

  @Column(columnDefinition = "TEXT")
  private String value;

  private String ressourceDependencies; // link to minio resources

  private Date lastEdited;
}
