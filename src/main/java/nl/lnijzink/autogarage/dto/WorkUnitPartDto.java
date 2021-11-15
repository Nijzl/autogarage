package nl.lnijzink.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkUnitPartDto {

//    private WorkUnit workUnit;
//
//    private Part part;
//
//    private Long amount;

    private List<WorkUnitPart> partsForm;

    public void addPartForm(WorkUnitPart part) {
        this.partsForm.add(part);}

}
