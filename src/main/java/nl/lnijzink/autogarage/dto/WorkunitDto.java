package nl.lnijzink.autogarage.dto;

import javax.validation.constraints.NotNull;

public class WorkunitDto {
    @NotNull
    private long id;

    @NotNull
    private String type;

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
}
