package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.model.WorkUnitAction;
import nl.lnijzink.autogarage.model.WorkUnitActionKey;

import java.util.Collection;

public interface WorkUnitActionService {

    Collection<WorkUnitAction> getAllWorkUnitActions();

    Collection<WorkUnit> getWorkUnitsByActionId(Long actionId);
    Collection<Action> getActionsByWorkUnitId(Long workUnitId);
    WorkUnitAction getWorkUnitActionById(Long workUnitId, Long actionId);
    WorkUnitActionKey addWorkUnitAction(Long workUnitId, Long actionId);

}
