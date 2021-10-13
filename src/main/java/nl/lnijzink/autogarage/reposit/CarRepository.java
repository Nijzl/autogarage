package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
