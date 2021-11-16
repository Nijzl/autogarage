package nl.lnijzink.autogarage.data;

import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.reposit.ActionRepository;
import nl.lnijzink.autogarage.reposit.PartRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderWorkUnit implements CommandLineRunner {

    private final WorkUnitRepository workUnitRepository;
    private final ActionRepository actionRepository;
    private final PartRepository partRepository;

    public DataLoaderWorkUnit(WorkUnitRepository workUnitRepository, ActionRepository actionRepository,
                              PartRepository partRepository){
        this.workUnitRepository = workUnitRepository;
        this.actionRepository = actionRepository;
        this.partRepository = partRepository;
    }


    @Override
    public void run(String...args) {

        // DataLoader Actions
        Action changeTyre = new Action("Change tyre", "Replacing a tyre with another tyre", 49.99F);
        Action changeOil = new Action("Change oil", "Refresh the motor oil", 9.99F);
        Action mot = new Action("MOT test", "Yearly Ministry of Transport test (APK)", 25.00F);

        changeTyre = actionRepository.save(changeTyre);
        changeOil = actionRepository.save(changeOil);
        mot = actionRepository.save(mot);


        // DataLoader Parts
        Part tyre = new Part("Tyre", 50.00F, 20L);
        Part oil = new Part("Oil", 5.00F, 5L);
        Part headLight = new Part("Head light", 1.99F, 40L);

        tyre = partRepository.save(tyre);
        oil = partRepository.save(oil);
        headLight = partRepository.save(headLight);
    }

}
