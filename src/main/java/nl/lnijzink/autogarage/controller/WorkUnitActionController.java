package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitActionDto;
import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
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
import java.util.ArrayList;

@Controller
@RequestMapping("/workUnitActions")
public class WorkUnitActionController {

    @Autowired
    WorkUnitService workunitService;
    CarService carService;
    EmployeeService employeeService;
    PartService partService;
    ActionService actionService;
    WorkUnitActionService workUnitActionService;
    WorkUnitRepository workUnitRepository;

    protected WorkUnitActionController(WorkUnitService service, CarService carService, EmployeeService employeeService,
                                       ActionService actionService, WorkUnitActionService workUnitActionService,
                                       WorkUnitRepository workUnitRepository){
            this.workunitService = service;
            this.carService = carService;
            this.employeeService = employeeService;
            this.actionService = actionService;
            this.workUnitActionService = workUnitActionService;
            this.workUnitRepository = workUnitRepository;
    }

    @GetMapping("/addWorkUnitAction/{id}")
    public String addAction(@PathVariable("id") Long id, Model model) {
        WorkUnitAction workUnitAction = new WorkUnitAction();
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        /*model.addAttribute("workUnits", workunitService.getWorkUnits());*/
        model.addAttribute("workUnitAction", workUnitAction);
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfActions", actionService.getActions());
        model.addAttribute("listOfWorkUnitActions", workUnitActionService.getWorkUnitActionsByWorkUnitId(id));
        return "WorkUnitActionUpdate";
    }

    @PostMapping("/addWorkUnitAction/{id}")
    public String addAction(@PathVariable("id") Long id, WorkUnitActionDto workUnitAction, Model model){
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        workUnitActionService.addWorkUnitAction(workUnitAction);
        return "redirect:/workUnitActions/addWorkUnitAction/{id}";
    }


/*
    @GetMapping("/")
    public String getWorkUnits(Model model) {
        var workUnits = workunitService.getWorkUnits();
        model.addAttribute("listOfWorkUnits", workUnits);
        return "WorkUnitsList";
    }

    @GetMapping("/create")
    public String createWorkUnit(Model model){
        model.addAttribute("workUnit", new WorkUnitDto());
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfParts", partService.getParts());
        model.addAttribute("listOfActions", actionService.getActions());
        return "WorkUnitForm";
    }

    @PostMapping("/create")
    public String createWorkUnit(@Valid @ModelAttribute("WorkUnit") WorkUnitDto wdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "WorkUnitForm";}

        var wu = workunitService.createWorkUnit(wdto);

*//*        for (WorkUnitPart wup : wdto.getWorkUnitParts()) {
            workUnitPartService.addWorkUnitPart(wu, wup.getId().getPartId());
        }

        for (WorkUnitAction wua : wdto.getWorkUnitActions()) {
            workUnitActionService.addWorkUnitAction(wu, wua.getId().getActionId());
        }*//*

        return "WorkUnitDisplay";
    }

*//*    @GetMapping("/check-not-agreed")
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
    }*//*

    @GetMapping("/addAction/{id}")
    public String addAction(@PathVariable("id") Long id, Model model) {
        WorkUnitAction workUnitAction = new WorkUnitAction();
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("workUnitAction", workUnitAction);
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfActions", actionService.getActions());
        model.addAttribute("listOfWorkUnitActions", new ArrayList<>());
        return "WorkUnitActionUpdate";
    }*/

}