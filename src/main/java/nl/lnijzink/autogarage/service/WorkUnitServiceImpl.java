package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.dto.WorkUnitDto;
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

    @Override
    public List<WorkUnitDto> getWorkUnits(){
        ArrayList<WorkUnitDto> pList = new ArrayList<>();
        workUnitRepository.findAll().forEach((p) -> pList.add(new WorkUnitDto(p.getId(), p.getType(),
                p.getCar(), p.getMechanic(), p.getWorkUnitParts(), p.getWorkUnitActions()
                )));
        return pList;
    }

    @Override
    public Long createWorkUnit(WorkUnitDto dto){
        WorkUnit workUnit = new WorkUnit(dto.getType(), dto.getCar(), dto.getMechanic());
        workUnitRepository.save(workUnit);
        return workUnit.getId();
    }

    @Override
    public WorkUnitDto getWorkUnit(Long id){
        Optional<WorkUnit> workUnit = workUnitRepository.findById(id);
        if(workUnit.isPresent()) {
            return new WorkUnitDto(workUnit.get().getType());
        }else {
            return new WorkUnitDto();
        }
    }

}
