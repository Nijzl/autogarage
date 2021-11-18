package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitActionDto;
import nl.lnijzink.autogarage.model.*;

import java.util.Collection;

public interface WorkUnitActionService {

    Collection<WorkUnitAction> getAllWorkUnitActions();

/*    Collection<WorkUnit> getWorkUnitsByActionId(Long actionId);*/
    Collection<WorkUnitAction> getWorkUnitActionsByWorkUnitId(Long workUnitId);
/*    WorkUnitAction getWorkUnitActionById(Long workUnitId, Long actionId);*/
    WorkUnitAction addWorkUnitAction(WorkUnitActionDto workUnitAction);

}
