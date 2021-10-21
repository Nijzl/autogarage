package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.reposit.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeerepo;

    @GetMapping("/employee")
    public String hello(){
        return "this is the employee page";
    }
    @PostMapping("/employee/create")
    public void addEmployee(@RequestParam String name){
        Employee emp1 = new Employee();
        emp1.setName(name);
        employeerepo.save(emp1);

    }
}
