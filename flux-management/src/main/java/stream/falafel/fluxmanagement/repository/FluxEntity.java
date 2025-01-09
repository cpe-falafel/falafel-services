package stream.falafel.fluxmanagement.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Data
@Entity(name = "flux")
public class FluxEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String uid;

  private String name;

  private String owner;

  private String value; // json with configuration => Destined to be an object

  private String ressourceDependencies; // link to minio resources

  private Date lastEdited;
}
