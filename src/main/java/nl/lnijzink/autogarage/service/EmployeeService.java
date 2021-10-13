package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    public long createEmployee(EmployeeDto edto);
    public EmployeeDto getEmployee(long id);
    public List<EmployeeDto> getEmployees();
}
