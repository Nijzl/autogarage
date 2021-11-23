package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.model.WorkUnitPartKey;
import nl.lnijzink.autogarage.model.WorkUnit;

import java.util.Collection;

public interface WorkUnitPartService {

    Collection<WorkUnitPart> getAllWorkUnitParts();
    Collection<WorkUnitPart> getWorkUnitPartsByWorkUnitId(Long workUnitId);
    WorkUnitPart addWorkUnitPart(WorkUnitPartDto workUnitPart);

}
