package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Double totalPartCost;

/*
    public WorkUnitPart() {
    }

    public WorkUnitPart(WorkUnitPartKey id, WorkUnit workUnit, Part part, Long amount, Double totalPartCost) {
        this.id = id;
        this.workUnit = workUnit;
        this.part = part;
        this.amount = amount;
        this.totalPartCost = totalPartCost;
    }
*/

/*    public WorkUnitPartKey getId() {
        return id;
    }

    public void setId(WorkUnitPartKey id) {
        this.id = id;
    }

    public WorkUnit getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(WorkUnit workUnit) {
        this.workUnit = workUnit;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getTotalPartCost() {
        return totalPartCost;
    }

    public void setTotalPartCost(Double totalPartCost) {
        this.totalPartCost = totalPartCost;
    }*/
}
