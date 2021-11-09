package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.model.WorkUnitPartKey;
import nl.lnijzink.autogarage.model.Workunit;

import java.util.Collection;

public interface WorkUnitPartService {

    Collection<WorkUnitPart> getAllWorkUnitParts();

    Collection<Workunit> getWorkUnitsByPartId(Long partId);
    Collection<Part> getPartsByWorkUnitId(Long workUnitId);
    WorkUnitPart getWorkUnitPartById(Long workUnitId, Long partId);
    WorkUnitPartKey addWorkUnitPart(Long workUnitId, Long partId);
}
