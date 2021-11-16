package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
