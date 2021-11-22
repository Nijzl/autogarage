package nl.lnijzink.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkUnitPartDto {

    Long workUnitId;
    Long partId;
    Long amount;

/*    public Long getWorkUnitId() {
        return workUnitId;
    }

    public void setWorkUnitId(Long workUnitId) {
        this.workUnitId = workUnitId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }*/
}
