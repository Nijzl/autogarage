package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.reposit.EmployeeRepository;
import nl.lnijzink.autogarage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    protected EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService){
        this.employeeRepository = employeeRepository; this.employeeService = employeeService;
    }

    @GetMapping("/{licencePlate}")
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/list")
    public List<EmployeeDto> getEmployees(Model model) {
        var employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return employees; //form maken
    }

    @PostMapping("/create")
    public void addEmployee(@RequestParam String name){
        Employee emp1 = new Employee();
        emp1.setName(name);
        employeeRepository.save(emp1);
    }



}
