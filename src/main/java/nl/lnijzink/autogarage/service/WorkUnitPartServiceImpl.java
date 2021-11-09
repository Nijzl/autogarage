package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.exception.RecordNotFoundException;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.model.WorkUnitPartKey;
import nl.lnijzink.autogarage.model.Workunit;
import nl.lnijzink.autogarage.reposit.PartRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitPartRepository;
import nl.lnijzink.autogarage.reposit.WorkunitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class WorkUnitPartServiceImpl implements WorkUnitPartService{
    private WorkunitRepository workunitRepository;
    private PartRepository partRepository;
    private WorkUnitPartRepository workUnitPartRepository;

    @Autowired
    public WorkUnitPartServiceImpl(WorkunitRepository workunitRepository, PartRepository partRepository,
                               WorkUnitPartRepository workUnitPartRepository) {
        this.workunitRepository = workunitRepository;
        this.partRepository = partRepository;
        this.workUnitPartRepository = workUnitPartRepository;
    }


    @Override
    public Collection<WorkUnitPart> getAllWorkUnitParts() {
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAll();
        return workUnitParts;
    }

    @Override
    public Collection<Workunit> getWorkUnitsByPartId(Long partId) {
        Collection<Workunit> workunits = new HashSet<>();
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAllByPartId(partId);
        for(WorkUnitPart workUnitPart : workUnitParts) {
            workunits.add(workUnitPart.getWorkunit());
        }
        return workunits;
    }

    @Override
    public Collection<Part> getPartsByWorkUnitId(Long workUnitId) {
        Collection<Part> parts = new HashSet<>();
        Collection<WorkUnitPart> workUnitParts = workUnitPartRepository.findAllByWorkunitId(workUnitId);
        for(WorkUnitPart workUnitPart : workUnitParts) {
            parts.add(workUnitPart.getPart());
        }
        return parts;    }

    @Override
    public WorkUnitPart getWorkUnitPartById(Long workUnitId, Long partId) {
        return workUnitPartRepository.findById(new WorkUnitPartKey(workUnitId, partId)).orElse(null);
    }

    @Override
    public WorkUnitPartKey addWorkUnitPart(Long workUnitId, Long partId) {
        var workUnitPart = new WorkUnitPart();
        if(!workunitRepository.existsById(workUnitId)) {throw new RecordNotFoundException();}
        Workunit workunit = workunitRepository.findById(workUnitId).orElse(null);
        if(!partRepository.existsById(partId)) {throw new RecordNotFoundException();}
        Part part = partRepository.findById(partId).orElse(null);
        workUnitPart.setWorkunit(workunit);
        workUnitPart.setPart(part);
        WorkUnitPartKey id = new WorkUnitPartKey(workUnitId, partId);
        workUnitPart.setId(id);
        return id;
    }
}
