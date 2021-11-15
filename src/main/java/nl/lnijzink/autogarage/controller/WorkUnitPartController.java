package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/workUnitParts")
public class WorkUnitPartController {

    @Autowired
    WorkUnitService workunitService;
    CarService carService;
    EmployeeService employeeService;
    PartService partService;
    ActionService actionService;
    WorkUnitPartService workUnitPartService;
    WorkUnitActionService workUnitActionService;

    protected WorkUnitPartController(WorkUnitService service, CarService carService, EmployeeService employeeService,
                                     PartService partService, ActionService actionService,
                                     WorkUnitActionService workUnitActionService,
                                     WorkUnitPartService workUnitPartService){this.workunitService =
            service; this.carService = carService; this.employeeService = employeeService; this.partService =
            partService; this.actionService = actionService; this.workUnitPartService = workUnitPartService; this.workUnitActionService =
            workUnitActionService;}




    @GetMapping("/create")
    public String showCreatePartForm(Model model){
        model.addAttribute("listOfParts", partService.getParts());
        List<WorkUnitPart> workUnitParts = new ArrayList<>();
        workUnitPartService.getAllWorkUnitParts().iterator().forEachRemaining(workUnitParts::add);

        model.addAttribute("form", new WorkUnitPartDto(workUnitParts));
        return "WorkUnitPartForm";
    }




/*


    @GetMapping("/create")
    public String createWorkUnit(Model model){
        model.addAttribute("workUnit", new WorkUnitDto());
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
*/
/*        model.addAttribute("listOfParts", partService.getParts());
        model.addAttribute("listOfActions", actionService.getActions());*//*

        return "WorkUnitForm";
    }

    @PostMapping("/create")
    public String createWorkUnit(@Valid @ModelAttribute("WorkUnit") WorkUnitDto wdto, BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            return "WorkUnitForm";}

        var wu = workunitService.createWorkUnit(wdto);

//        for (WorkUnitPart wup : wdto.getWorkUnitParts()) {
//            workUnitPartService.addWorkUnitPart(wu, wup.getId().getPartId());
//        }
//
//        for (WorkUnitAction wua : wdto.getWorkUnitActions()) {
//            workUnitActionService.addWorkUnitAction(wu, wua.getId().getActionId());
//        }

        return "WorkUnitDisplay";
    }

    @GetMapping("/check-not-agreed")
    public String checkNotAgreed(){
        return "WorkUnitQuintanceCheck";
    }

    @GetMapping("/repair")
    public String repair(){
        return "WorkUnitRepair";
    }

    @GetMapping("/quintance/check")
    public String quintanceCheck(){
        return "WorkUnitQuintanceCheck";
    }

    @GetMapping("/quintance/repair")
    public String quintanceRepair(){
        return "WorkUnitQuintanceRepair";
    }
*/



}
