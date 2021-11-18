package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.WorkUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkUnitRepository extends JpaRepository<WorkUnit, Long> {

}
