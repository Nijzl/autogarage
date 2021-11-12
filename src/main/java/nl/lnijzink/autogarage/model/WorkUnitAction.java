package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
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
    @JoinColumn(name = "part_id")
    private Action action;

    private Long amount;

}
