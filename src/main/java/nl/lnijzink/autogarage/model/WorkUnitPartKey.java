package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class WorkUnitPartKey implements Serializable {
    
    @Column(name = "work_unit_id")
    private Long workUnitId;
    
    @Column(name = "part_id")
    private Long partId;
    
    public WorkUnitPartKey(){};

    public WorkUnitPartKey(Long workUnitId, Long partId) {
        this.workUnitId = workUnitId;
        this.partId = partId;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        WorkUnitPartKey that = (WorkUnitPartKey) o;
        return workUnitId.equals(that.workUnitId) && partId.equals(that.partId);
    }
    
    @Override
    public int hashCode() {return Objects.hash(workUnitId, partId);}

}
