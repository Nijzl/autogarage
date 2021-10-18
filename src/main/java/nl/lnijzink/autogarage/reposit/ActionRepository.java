package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
