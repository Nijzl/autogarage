package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkunitDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Employee;

public interface WorkunitService {
    public long createWorkunit(WorkunitDto workunitDto);
    public WorkunitDto getWorkunit(long id);
}
