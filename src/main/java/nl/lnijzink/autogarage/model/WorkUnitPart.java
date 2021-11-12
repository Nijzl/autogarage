package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class WorkUnitPart {

    @EmbeddedId
    private WorkUnitPartKey id;

    @ManyToOne
    @JsonIgnore
    @MapsId("workUnitId")
    @JoinColumn(name = "work_unit_id")
    private WorkUnit workUnit;

    @ManyToOne
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    private Part part;

    private Long amount;
}
