package stream.falafel.fluxmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FluxJPARepository extends JpaRepository<FluxEntity, String> {
    public FluxEntity findByUid(String uid);
    public List<FluxEntity> findByOwner(String owner);
}
