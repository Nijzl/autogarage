package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.RepairStatus;
import nl.lnijzink.autogarage.model.WorkUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WorkUnitRepository extends JpaRepository<WorkUnit, Long> {
    Collection<WorkUnit> findAllByRepairStatus(RepairStatus repairStatus);
}
