package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {

}
