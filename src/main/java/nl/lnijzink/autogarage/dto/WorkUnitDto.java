package nl.lnijzink.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.lnijzink.autogarage.model.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
public class WorkUnitDto {

    @NotNull
    private Long id;

    private Type type;

    private Car car;

    private Employee mechanic;

    private Collection<WorkUnitPart> workUnitParts;

    private Collection<WorkUnitAction> workUnitActions;

    public WorkUnitDto(){}

    public WorkUnitDto(Type type){
        this.type = type;
    }

}
