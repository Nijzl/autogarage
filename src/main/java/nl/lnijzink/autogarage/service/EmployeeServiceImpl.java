package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.reposit.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){this.employeeRepository = employeeRepository;}

    @Override
    public long createEmployee(EmployeeDto edto){
        Employee e = new Employee(edto.getName(), edto.getRole());
        employeeRepository.save(e);
        return e.getId();
    }

    @Override
    public EmployeeDto getEmployee(Long id){
        Employee p = employeeRepository.findById(id).get();
        return new EmployeeDto(p.getId(), p.getName(), p.getRole());
    }

    @Override
    public List<EmployeeDto> getEmployees(){
        ArrayList<EmployeeDto> pList = new ArrayList<>();
        employeeRepository.findAll().forEach((p) -> pList.add(new EmployeeDto(p.getId(), p.getName(), p.getRole()
        )));
        return pList;
    }

}
