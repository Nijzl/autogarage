package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Customer;
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

    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "EmployeeUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @Valid Employee employee,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "EmployeeUpdate";
        }
        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeRepository.delete(employee);
        return "redirect:/employees/";
    }

}
