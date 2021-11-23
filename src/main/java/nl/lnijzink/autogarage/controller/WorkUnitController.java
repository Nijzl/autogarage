package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.RepairStatus;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import nl.lnijzink.autogarage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/workUnit")
public class WorkUnitController {

    @Autowired
    WorkUnitService workUnitService;
    CarService carService;
    EmployeeService employeeService;
    PartService partService;
    ActionService actionService;
    WorkUnitPartService workUnitPartService;
    WorkUnitActionService workUnitActionService;
    WorkUnitRepository workUnitRepository;

    protected WorkUnitController(WorkUnitService workUnitService, CarService carService,
                                 EmployeeService employeeService,
                                 PartService partService, ActionService actionService,
                                 WorkUnitActionService workUnitActionService,
                                 WorkUnitPartService workUnitPartService, WorkUnitRepository workUnitRepository) {
        this.workUnitService = workUnitService;
        this.carService = carService;
        this.employeeService = employeeService;
        this.partService = partService;
        this.actionService = actionService;
        this.workUnitPartService = workUnitPartService;
        this.workUnitActionService = workUnitActionService;
        this.workUnitRepository = workUnitRepository;
    }


    // Get List of Work Units
    @GetMapping("/")
    public String getWorkUnits(Model model) {
        var workUnits = workUnitService.getWorkUnits();
        model.addAttribute("listOfWorkUnits", workUnits);
        return "WorkUnitsList";
    }

    // Get Single Work Unit
    @GetMapping("/view/{id}")
    public String getWorkUnit(@PathVariable("id") Long id, Model model) {
        WorkUnitDto workUnit = workUnitService.getWorkUnit(id);
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        return "WorkUnitGet";
    }

    // Create new Work Unit
    @GetMapping("/create")
    public String createWorkUnit(Model model) {
        model.addAttribute("workUnit", new WorkUnitDto());
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        return "WorkUnitForm";
    }

    @PostMapping("/create")
    public String createWorkUnit(@Valid @ModelAttribute("WorkUnit") WorkUnitDto wdto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "WorkUnitForm";}
        workUnitService.createWorkUnit(wdto);
        return "WorkUnitDisplay";
    }

    // Update Work Unit
    @GetMapping("/update/{id}")
    public String updateWorkUnit(@PathVariable("id") Long id, Model model) {
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfParts", partService.getParts());
        model.addAttribute("listOfActions", actionService.getActions());
        model.addAttribute("listOfWorkUnitParts", workUnitPartService.getAllWorkUnitParts());
        model.addAttribute("listOfWorkUnitActions", workUnitActionService.getAllWorkUnitActions());
        return "WorkUnitUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateWorkUnit(@PathVariable("id") Long id, @Valid WorkUnit workUnit,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            workUnit.setId(id);
            return "WorkUnitUpdate";
        }
        workUnitRepository.save(workUnit);
        return "redirect:/workUnit/";
    }

    // Delete Work Unit
    @GetMapping("/delete/{id}")
    public String deleteWorkUnit(@PathVariable("id") Long id, Model model) {
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        workUnitRepository.delete(workUnit);
        return "redirect:/workUnit/";
    }

    // Get List of Work Units with a specific repairStatus
    @RequestMapping("/callList/")
    public String getAllByRepairStatus(@RequestParam(value = "repairStatus", required = false) RepairStatus repairStatus,
                                       Model model){
        var workUnit = new WorkUnit();
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("repairStatus", repairStatus);
        // if there is no workUnit with this particular repairStatus, return list with all workUnits
        if (repairStatus == null) {
            var repairs = workUnitService.getWorkUnits();
            model.addAttribute("listOfWorkUnits", repairs);
            return "CallListRepairStatusForm";
        // else return list of workUnits with particular repairStatus
        } else{
            var repairs = workUnitService.getAllByRepairStatus(repairStatus);
            model.addAttribute("listOfWorkUnits", repairs);
            return "CallListRepairStatusForm";
        }
    }

}
