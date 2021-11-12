package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.model.WorkUnitAction;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import nl.lnijzink.autogarage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/workUnit")
public class WorkUnitController {

    @Autowired
    WorkUnitService workunitService;
    CarService carService;
    EmployeeService employeeService;
    PartService partService;
    WorkUnitPartService workUnitPartService;
    WorkUnitActionService workUnitActionService;

    protected WorkUnitController(WorkUnitService service, CarService carService, EmployeeService employeeService,
                                 PartService partService, WorkUnitActionService workUnitActionService,
                                 WorkUnitPartService workUnitPartService){this.workunitService =
            service; this.carService = carService; this.employeeService = employeeService; this.partService =
            partService; this.workUnitPartService = workUnitPartService; this.workUnitActionService =
            workUnitActionService;}

    @GetMapping("/create")
    public String createWorkUnit(Model model){
        model.addAttribute("workUnit", new WorkUnitDto());
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfParts", partService.getParts());
        return "WorkUnitForm";
    }

    @PostMapping("/create")
    public String createWorkUnit(@Valid @ModelAttribute("WorkUnit") WorkUnitDto wdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "WorkUnitForm";}

        workunitService.createWorkUnit(wdto);
        var wu = workunitService.createWorkUnit(wdto);

        for (WorkUnitPart wup : wdto.getWorkUnitParts()) {
            workUnitPartService.addWorkUnitPart(wu, wup.getId().getPartId());
        }

        for (WorkUnitAction wua : wdto.getWorkUnitActions()) {
            workUnitActionService.addWorkUnitAction(wu, wua.getId().getActionId());
        }

        return "WorkUnitForm";
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



}
