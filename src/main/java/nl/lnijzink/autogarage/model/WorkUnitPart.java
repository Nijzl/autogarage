package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class WorkUnitPart {

    @EmbeddedId
    private WorkUnitPartKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("workUnitId")
    @JoinColumn(name = "work_unit_id")
    private Workunit workunit;

    @ManyToOne
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    private Part part;


}
