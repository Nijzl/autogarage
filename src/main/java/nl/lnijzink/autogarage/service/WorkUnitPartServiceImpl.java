package nl.lnijzink.autogarage.service;

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

    @Override
    public Collection<WorkUnit> getWorkUnitsByPartId(Long partId) {
        Collection<WorkUnit> workUnits = new HashSet<>();
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAllByPartId(partId);
        for(WorkUnitPart workUnitPart : workUnitParts) {
            workUnits.add(workUnitPart.getWorkUnit());
        }
        return workUnits;
    }

    @Override
    public Collection<Part> getPartsByWorkUnitId(Long workUnitId) {
        Collection<Part> parts = new HashSet<>();
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAllByWorkUnitId(workUnitId);
        for(WorkUnitPart workUnitPart : workUnitParts) {
            parts.add(workUnitPart.getPart());
        }
        return parts;
    }

    @Override
    public WorkUnitPart getWorkUnitPartById(Long workUnitId, Long partId) {
        return workUnitPartRepository.findById(new WorkUnitPartKey(workUnitId, partId)).orElse(null);
    }

    @Override
    public WorkUnitPartKey addWorkUnitPart(Long workUnitId, Long partId) {
        var workUnitPart = new WorkUnitPart();
        if(!workUnitRepository.existsById(workUnitId)) {throw new RecordNotFoundException();}
        WorkUnit workUnit = workUnitRepository.findById(workUnitId).orElse(null);
        if(!partRepository.existsById(partId)) {throw new RecordNotFoundException();}
        Part part = partRepository.findById(partId).orElse(null);
        workUnitPart.setWorkUnit(workUnit);
        workUnitPart.setPart(part);
        WorkUnitPartKey id = new WorkUnitPartKey(workUnitId, partId);
        workUnitPart.setId(id);
        workUnitPartRepository.save(workUnitPart);
        return id;
    }

}
