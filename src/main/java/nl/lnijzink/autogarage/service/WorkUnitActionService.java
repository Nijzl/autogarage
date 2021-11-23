package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitActionDto;
import nl.lnijzink.autogarage.model.*;

import java.util.Collection;

public interface WorkUnitActionService {

    Collection<WorkUnitAction> getAllWorkUnitActions();
    Collection<WorkUnitAction> getWorkUnitActionsByWorkUnitId(Long workUnitId);
    WorkUnitAction addWorkUnitAction(WorkUnitActionDto workUnitAction);

}
