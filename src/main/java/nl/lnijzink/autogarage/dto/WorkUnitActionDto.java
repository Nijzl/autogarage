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
public class WorkUnitActionDto {

/*    private WorkUnit workUnit;

    private Action action;

    private Long amount;*/

    private List<WorkUnitAction> actions;

    public void addAction(WorkUnitAction action) {
        this.actions.add(action);
    }

}
