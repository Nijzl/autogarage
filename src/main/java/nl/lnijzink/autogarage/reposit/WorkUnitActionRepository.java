package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.WorkUnitAction;
import nl.lnijzink.autogarage.model.WorkUnitActionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface WorkUnitActionRepository extends JpaRepository<WorkUnitAction, WorkUnitActionKey> {
    Collection<WorkUnitAction> findAllByWorkUnitId(Long workUnitId);

}
