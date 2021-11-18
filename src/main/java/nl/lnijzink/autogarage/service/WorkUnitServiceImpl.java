package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkUnitServiceImpl implements WorkUnitService {

    private final WorkUnitRepository workUnitRepository;

    public WorkUnitServiceImpl(WorkUnitRepository workUnitRepository){this.workUnitRepository = workUnitRepository;}


    // Get List of Work Units
    @Override
    public List<WorkUnitDto> getWorkUnits(){
        ArrayList<WorkUnitDto> pList = new ArrayList<>();
        workUnitRepository.findAll().forEach((p) -> pList.add(new WorkUnitDto(p.getId(), p.getType(),
                p.getCar(), p.getMechanic(), p.getCustomerStatus(), p.getRepairStatus()
//                p.getWorkUnitParts(),
//                p.getWorkUnitActions()
                )));
        return pList;
    }

    // Get Single Work Unit
    @Override
    public WorkUnitDto getWorkUnit(Long id){
            WorkUnit workUnit = workUnitRepository.findById(id).get();
            return new WorkUnitDto(workUnit.getId(), workUnit.getType(), workUnit.getCar(), workUnit.getMechanic(),
                    workUnit.getCustomerStatus(), workUnit.getRepairStatus());
    }

    // Create New Work Unit
    @Override
    public Long createWorkUnit(WorkUnitDto dto){
        WorkUnit workUnit = new WorkUnit(dto.getType(), dto.getCar(), dto.getMechanic(), dto.getCustomerStatus(),
                dto.getRepairStatus());
        workUnitRepository.save(workUnit);
        return workUnit.getId();
    }

    // Delete Work Unit
    @Override
    public void deleteWorkUnit(Long id){
        boolean exists = workUnitRepository.existsById(id);
        if(exists){
            workUnitRepository.deleteById(id);
        }
    }

}
