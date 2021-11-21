package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.exception.RecordNotFoundException;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.model.WorkUnitPartKey;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.reposit.PartRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitPartRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class WorkUnitPartServiceImpl implements WorkUnitPartService {

    private WorkUnitRepository workUnitRepository;
    private PartRepository partRepository;
    private WorkUnitPartRepository workUnitPartRepository;

    @Autowired
    public WorkUnitPartServiceImpl(WorkUnitRepository workUnitRepository, PartRepository partRepository,
                                   WorkUnitPartRepository workUnitPartRepository) {
        this.workUnitRepository = workUnitRepository;
        this.partRepository = partRepository;
        this.workUnitPartRepository = workUnitPartRepository;
    }

    @Override
    public Collection<WorkUnitPart> getAllWorkUnitParts() {
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAll();
        return workUnitParts;
    }

/*    @Override
    public Collection<WorkUnit> getWorkUnitsByPartId(Long partId) {
        Collection<WorkUnit> workUnits = new HashSet<>();
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAllByPartId(partId);
        for(WorkUnitPart workUnitPart : workUnitParts) {
            workUnits.add(workUnitPart.getWorkUnit());
        }
        return workUnits;
    }*/

    @Override
    public Collection<WorkUnitPart> getWorkUnitPartsByWorkUnitId(Long workUnitId) {
        Collection<WorkUnitPart> partList = new HashSet<>();
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAllByWorkUnitId(workUnitId);
        for(WorkUnitPart workUnitPart : workUnitParts) {
            partList.add(workUnitPart);
        }
        return partList;
    }

/*    @Override
    public WorkUnitPart getWorkUnitPartById(Long workUnitId, Long partId) {
        return workUnitPartRepository.findById(new WorkUnitPartKey(workUnitId, partId)).orElse(null);
    }*/

    @Override
    public WorkUnitPart addWorkUnitPart(WorkUnitPartDto workUnitPart) {
        var workUnitPart1 = new WorkUnitPart();
        if(!workUnitRepository.existsById(workUnitPart.getWorkUnitId())) {throw new RecordNotFoundException();}
        WorkUnit workUnit = workUnitRepository.findById(workUnitPart.getWorkUnitId()).orElse(null);
        if(!partRepository.existsById(workUnitPart.getPartId())) {throw new RecordNotFoundException();}
        Part part = partRepository.findById(workUnitPart.getPartId()).orElse(null);
        workUnitPart1.setWorkUnit(workUnit);
        workUnitPart1.setPart(part);
        WorkUnitPartKey id = new WorkUnitPartKey(workUnitPart.getWorkUnitId(), workUnitPart.getPartId());
        workUnitPart1.setId(id);
        workUnitPart1.setAmount(workUnitPart.getAmount());
        workUnitPart1.setTotalPartCost(part.getPrice()* workUnitPart.getAmount());
        workUnitPartRepository.save(workUnitPart1);
        return workUnitPart1;
    }

}
