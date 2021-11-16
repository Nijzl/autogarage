package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
