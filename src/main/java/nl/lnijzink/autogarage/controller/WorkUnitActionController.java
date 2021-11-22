package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitActionDto;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.model.WorkUnitAction;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import nl.lnijzink.autogarage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    // Add Actions to Work Unit
    @GetMapping("/addWorkUnitAction/{id}")
    public String addAction(@PathVariable("id") Long id, Model model) {
        WorkUnitAction workUnitAction = new WorkUnitAction();
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
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

}