package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;

public interface WorkUnitService {

    public long createWorkUnit(WorkUnitDto workUnitDto);
    public WorkUnitDto getWorkUnit(Long id);

}
