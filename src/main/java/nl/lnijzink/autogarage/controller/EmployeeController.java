package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.reposit.EmployeeRepository;
import nl.lnijzink.autogarage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/")
    public String getEmployees(Model model) {
        var employees = employeeService.getEmployees();
        model.addAttribute("listOfEmployees", employees);
        return "EmployeesList";
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("Employee", new EmployeeDto());
        return "EmployeeForm";
    }

    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute("Employee") EmployeeDto employeeDto,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "EmployeeForm";
        }
        employeeService.createEmployee(employeeDto);
        return "EmployeeDisplay";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("employeeId") Long id){
        employeeService.deleteEmployee(id);
        return "EmployeeDeleteDisplay";
    }

}
