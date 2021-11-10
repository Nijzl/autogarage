package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.exception.RecordNotFoundException;
import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.model.WorkUnitAction;
import nl.lnijzink.autogarage.model.WorkUnitActionKey;
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

    @Override
    public Collection<WorkUnit> getWorkUnitsByActionId(Long actionId){
        Collection<WorkUnit> workUnits = new HashSet<>();
        Collection<WorkUnitAction> workUnitActions = workUnitActionRepository.findAllByActionId(actionId);
        for(WorkUnitAction workUnitAction : workUnitActions){
            workUnits.add(workUnitAction.getWorkUnit());
        }
        return workUnits;
    }

    @Override
    public Collection<Action> getActionsByWorkUnitId(Long workUnitId){
        Collection<Action> actions = new HashSet<>();
        Collection<WorkUnitAction> workUnitActions = workUnitActionRepository.findAllByWorkUnitId(workUnitId);
        for(WorkUnitAction workUnitAction : workUnitActions){
            actions.add(workUnitAction.getAction());
        }
        return actions;
    }

    @Override
    public WorkUnitAction getWorkUnitActionById(Long workUnitId, Long actionId){
        return workUnitActionRepository.findById(new WorkUnitActionKey(workUnitId, actionId)).orElse(null);
    }

    @Override
    public WorkUnitActionKey addWorkUnitAction(Long workUnitId, Long actionId){
        var workUnitAction = new WorkUnitAction();
        if(!workUnitRepository.existsById(workUnitId)) {throw new RecordNotFoundException();}
        WorkUnit workUnit = workUnitRepository.findById(workUnitId).orElse(null);
        if(!actionRepository.existsById(actionId)) {throw new RecordNotFoundException();}
        Action action = actionRepository.findById(actionId).orElse(null);
        workUnitAction.setWorkUnit(workUnit);
        workUnitAction.setAction(action);
        WorkUnitActionKey id = new WorkUnitActionKey(workUnitId, actionId);
        workUnitAction.setId(id);
        return id;
    }

}
