package nl.lnijzink.autogarage.data;

import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.reposit.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderEmployees implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public DataLoaderEmployees(EmployeeRepository employeeRepository){this.employeeRepository = employeeRepository;}


    @Override
    public void run(String...args) {

        Employee mechanic1 = new Employee("Tip Top", "mechanic");
        Employee administrator1 = new Employee("Bear Grills", "administrator");
        Employee backoffice1 = new Employee("Tom Jones", "backoffice");

        mechanic1 = employeeRepository.save(mechanic1);
        administrator1 = employeeRepository.save(administrator1);
        backoffice1 = employeeRepository.save(backoffice1);
    }

}
