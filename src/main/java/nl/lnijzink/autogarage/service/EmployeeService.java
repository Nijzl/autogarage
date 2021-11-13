package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public Long createEmployee(EmployeeDto edto);
    public EmployeeDto getEmployee(Long id);
    public List<EmployeeDto> getEmployees();
    public void deleteEmployee(Long id);

}
