package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;

public interface WorkUnitService {

    public Long createWorkUnit(WorkUnitDto workUnitDto);
    public WorkUnitDto getWorkUnit(Long id);

}
