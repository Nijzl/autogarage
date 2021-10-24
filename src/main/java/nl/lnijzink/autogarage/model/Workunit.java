package nl.lnijzink.autogarage.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class Workunit
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String Type;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", referencedColumnName = "ïd")
    private Employee mechanic;

    @ManyToOne
    @Nullable
    @MapsId("id")
    @JoinColumn(name = "id")
    Action action;

    @ManyToOne
    @Nullable
    @MapsId("id")
    @JoinColumn(name = "id")
    Part part;

    @OneToOne
    @Nullable
    @JoinColumn(name = "workunit_id", referencedColumnName = "id")
    private Workunit workunit;

    public Workunit() {
    }

    public Workunit(String type, Car car, Employee mechanic) {
        Type = type;
        this.car = car;
        this.mechanic = mechanic;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Workunit getWorkunit() {
        return workunit;
    }

    public void setWorkunit(Workunit workunit) {
        this.workunit = workunit;
    }
}
