package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.model.WorkUnitPartKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface WorkUnitPartRepository extends JpaRepository<WorkUnitPart, WorkUnitPartKey> {
    Collection<WorkUnitPart> findAllByPartId(Long partId);
    Collection<WorkUnitPart> findAllByWorkUnitId(Long workUnitId);

}
