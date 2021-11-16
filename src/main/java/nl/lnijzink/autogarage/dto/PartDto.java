package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("1.00e6")
    private Float price;

    @NotNull
    @Min(1)
    @Max((long) 1.0e6)
    private Long quantity;

}
