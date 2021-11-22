package nl.lnijzink.autogarage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class WorkUnitActionKey implements Serializable {

    @Column(name = "work_unit_id")
    private Long workUnitId;

    @Column(name = "action_id")
    private Long actionId;

/*    public WorkUnitActionKey(){}

    public WorkUnitActionKey(Long workUnitId, Long actionId){
        this.workUnitId = workUnitId;
        this.actionId = actionId;
    }*/

    @Override
    public boolean equals(Object w){
        if(this == w) return true;
        if(w == null || getClass() != w.getClass()) return false;
        WorkUnitActionKey that = (WorkUnitActionKey) w;
        return workUnitId.equals(that.workUnitId) && actionId.equals(that.actionId);
    }

    @Override
    public int hashCode(){return Objects.hash(workUnitId, actionId);}

}
