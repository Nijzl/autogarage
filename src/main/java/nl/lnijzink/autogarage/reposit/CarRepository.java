package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, String> {
//    Optional<Car> findByLicencePlate(String licencePLate);
}
