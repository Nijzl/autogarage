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
        Action changeTyre = new Action("Change tyre", "Replacing a tyre with another tyre", 49.99);
        Action changeOil = new Action("Change oil", "Refresh the motor oil", 9.99);
        Action changeHeadLight = new Action("Change head light", "Replace one head light with another head light",
                5.99);
        Action fixWindShield = new Action("Fix wind shield", "Repair problems with the wind shield", 14.90);
        Action mot = new Action("MOT test", "Yearly Ministry of Transport test (APK)", 25.00);

        changeTyre = actionRepository.save(changeTyre);
        changeOil = actionRepository.save(changeOil);
        changeHeadLight = actionRepository.save(changeHeadLight);
        fixWindShield = actionRepository.save(fixWindShield);
        mot = actionRepository.save(mot);


        // DataLoader Parts
        Part tyre = new Part("Tyre", 50.00, 20L);
        Part oil = new Part("Oil", 5.00, 5L);
        Part headLight = new Part("Head light", 1.99, 40L);
        Part windShield = new Part("Wind shield", 100.00, 10L);
        Part blinker = new Part("Blinker", 3.68, 50L);

        tyre = partRepository.save(tyre);
        oil = partRepository.save(oil);
        headLight = partRepository.save(headLight);
        windShield = partRepository.save(windShield);
        blinker = partRepository.save(blinker);
    }

}
