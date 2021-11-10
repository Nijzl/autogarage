package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkUnitServiceImpl implements WorkUnitService {

    private final WorkUnitRepository workUnitRepository;

    public WorkUnitServiceImpl(WorkUnitRepository workUnitRepository){this.workUnitRepository = workUnitRepository;}

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
