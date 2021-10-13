package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
