package nl.lnijzink.autogarage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkUnitAction {

    @EmbeddedId
    private WorkUnitActionKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("workUnitId")
    @JoinColumn(name = "work_unit_id")
    private WorkUnit workUnit;

    @ManyToOne
    @MapsId("actionId")
    @JoinColumn(name = "action_id")
    private Action action;

    private Long amount;

    private Double totalActionCost;

}
