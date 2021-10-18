package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.*;

public class ActionDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 250)
    private String description;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0e6")
    private float price;

    public ActionDto(){}

    public ActionDto(long id, String name, String description, float price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public float getPrice() {return price;}

    public void setPrice(float price) {this.price = price;}
}
