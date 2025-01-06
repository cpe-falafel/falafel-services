package stream.falafel.fluxmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FluxJPARepository extends JpaRepository<FluxEntity, String> {
     FluxEntity findByUid(String uid);
     List<FluxEntity> findByOwner(String owner);
}
