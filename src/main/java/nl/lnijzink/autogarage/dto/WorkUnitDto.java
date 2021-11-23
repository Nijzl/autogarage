package nl.lnijzink.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.*;

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

}
