package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Workunit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkunitRepository extends JpaRepository<Workunit, Long> {
}
