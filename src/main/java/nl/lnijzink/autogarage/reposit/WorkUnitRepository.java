package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.WorkUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkUnitRepository extends JpaRepository<WorkUnit, Long> {

}
