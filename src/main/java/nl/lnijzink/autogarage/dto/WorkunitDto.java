package nl.lnijzink.autogarage.dto;

import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Employee;

import javax.validation.constraints.NotNull;

public class WorkunitDto {
    @NotNull
    private long id;

    @NotNull
    private String type;

    private Car car;

    private Employee mechanic;

    public WorkunitDto(){}

    public WorkunitDto(String type){
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee getMechanic() {
        return mechanic;
    }

    public void setMechanic(Employee mechanic) {
        this.mechanic = mechanic;
    }
}
