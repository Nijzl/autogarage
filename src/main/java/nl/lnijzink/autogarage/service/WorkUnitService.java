package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.WorkUnit;

import java.util.List;

public interface WorkUnitService {

    public List<WorkUnitDto> getWorkUnits();
    public Long createWorkUnit(WorkUnitDto workUnitDto);
    public WorkUnitDto getWorkUnit(Long id);

}
