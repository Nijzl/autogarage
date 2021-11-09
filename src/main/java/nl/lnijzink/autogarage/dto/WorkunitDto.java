package nl.lnijzink.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.model.Type;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WorkunitDto {
    @NotNull
    private long id;

    private Type type;

    private Car car;

    private Employee mechanic;

    public WorkunitDto(){}

    public WorkunitDto(Type type){
        this.type = type;
    }

}
