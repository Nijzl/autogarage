package nl.lnijzink.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkUnitDto {

    private Long id;

    private Type type;

    private Car car;

    private String mechanic;

    private CustomerStatus customerStatus;

    private RepairStatus repairStatus;

    public WorkUnitDto(Type type, Car car, String mechanic, CustomerStatus customerStatus, RepairStatus repairStatus) {
        this.type = type;
        this.car = car;
        this.mechanic = mechanic;
        this.customerStatus = customerStatus;
        this.repairStatus = repairStatus;
    }

}
