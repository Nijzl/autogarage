package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.WorkUnit;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WorkUnitService {

    public List<WorkUnitDto> getWorkUnits();
    public WorkUnitDto getWorkUnit(Long id);
    public Long createWorkUnit(WorkUnitDto workUnitDto);
    public void deleteWorkUnit(Long id);

}
