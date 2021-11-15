package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmailEquals(String email);


}
