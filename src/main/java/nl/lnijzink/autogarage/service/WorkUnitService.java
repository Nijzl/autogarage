package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.WorkUnit;

public interface WorkUnitService {

    public Long createWorkUnit(WorkUnitDto workUnitDto);
    public WorkUnitDto getWorkUnit(Long id);

}
