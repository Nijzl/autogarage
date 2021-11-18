package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.model.WorkUnit;
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
    WorkUnitRepository workUnitRepository;

    protected WorkUnitPartController(WorkUnitService service, CarService carService, EmployeeService employeeService,
                                     PartService partService, ActionService actionService,
                                     WorkUnitActionService workUnitActionService,
                                     WorkUnitPartService workUnitPartService,
                                     WorkUnitRepository workUnitRepository){
            this.workunitService = service;
            this.carService = carService;
            this.employeeService = employeeService;
            this.partService = partService;
            this.actionService = actionService;
            this.workUnitPartService = workUnitPartService;
            this.workUnitActionService = workUnitActionService;
            this.workUnitRepository = workUnitRepository;
    }


    @GetMapping("/addWorkUnitPart/{id}")
    public String addPart(@PathVariable("id") Long id, Model model) {
        WorkUnitPart workUnitPart = new WorkUnitPart();
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("workUnits", workunitService.getWorkUnits());
        model.addAttribute("workUnitPart", workUnitPart);
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfParts", partService.getParts());
        model.addAttribute("listOfWorkUnitParts", workUnitPartService.getWorkUnitPartsByWorkUnitId(id));
        return "WorkUnitPartUpdate";
    }

    @PostMapping("/addWorkUnitPart/{id}")
    public String addPart(@PathVariable("id")  Long id, WorkUnitPartDto workUnitPart){
        workUnitPartService.addWorkUnitPart(workUnitPart);
        return "redirect:/workUnit/";
    }

}
