package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitActionDto;
import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.exception.RecordNotFoundException;
import nl.lnijzink.autogarage.model.*;
import nl.lnijzink.autogarage.reposit.ActionRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitActionRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class WorkUnitActionServiceImpl implements WorkUnitActionService {

    private WorkUnitRepository workUnitRepository;
    private ActionRepository actionRepository;
    private WorkUnitActionRepository workUnitActionRepository;

    @Autowired
    public WorkUnitActionServiceImpl(WorkUnitRepository workUnitRepository, ActionRepository actionRepository,
                                     WorkUnitActionRepository workUnitActionRepository){
        this.workUnitRepository = workUnitRepository;
        this.actionRepository = actionRepository;
        this.workUnitActionRepository = workUnitActionRepository;
    }

    @Override
    public Collection<WorkUnitAction> getAllWorkUnitActions(){
        Collection<WorkUnitAction> workUnitActions = workUnitActionRepository.findAll();
        return workUnitActions;
    }

/*    @Override
    public Collection<WorkUnit> getWorkUnitsByActionId(Long actionId){
        Collection<WorkUnit> workUnits = new HashSet<>();
        Collection<WorkUnitAction> workUnitActions = workUnitActionRepository.findAllByActionId(actionId);
        for(WorkUnitAction workUnitAction : workUnitActions){
            workUnits.add(workUnitAction.getWorkUnit());
        }
        return workUnits;
    }*/

    @Override
    public Collection<WorkUnitAction> getWorkUnitActionsByWorkUnitId(Long workUnitId) {
        Collection<WorkUnitAction> actionList = new HashSet<>();
        Collection<WorkUnitAction> workUnitActions = workUnitActionRepository.findAllByWorkUnitId(workUnitId);
        for(WorkUnitAction workUnitAction : workUnitActions) {
            actionList.add(workUnitAction);
        }
        return actionList;
    }

/*    @Override
    public WorkUnitAction getWorkUnitActionById(Long workUnitId, Long actionId){
        return workUnitActionRepository.findById(new WorkUnitActionKey(workUnitId, actionId)).orElse(null);
    }*/

    @Override
    public WorkUnitAction addWorkUnitAction(WorkUnitActionDto workUnitAction) {
        var workUnitAction1 = new WorkUnitAction();
        if(!workUnitRepository.existsById(workUnitAction.getWorkUnitId())) {throw new RecordNotFoundException();}
        WorkUnit workUnit = workUnitRepository.findById(workUnitAction.getWorkUnitId()).orElse(null);
        if(!actionRepository.existsById(workUnitAction.getActionId())) {throw new RecordNotFoundException();}
        Action action = actionRepository.findById(workUnitAction.getActionId()).orElse(null);
        workUnitAction1.setWorkUnit(workUnit);
        workUnitAction1.setAction(action);
        WorkUnitActionKey id = new WorkUnitActionKey(workUnitAction.getWorkUnitId(), workUnitAction.getActionId());
        workUnitAction1.setId(id);
        workUnitAction1.setAmount(workUnitAction.getAmount());
        workUnitAction1.setTotalActionCost(action.getPrice()* workUnitAction1.getAmount());
        workUnitActionRepository.save(workUnitAction1);
        return workUnitAction1;
    }

}
