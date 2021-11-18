package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import nl.lnijzink.autogarage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/create")
    public String createWorkUnit(Model model) {
        model.addAttribute("workUnit", new WorkUnitDto());
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
//        model.addAttribute("listOfParts", partService.getParts());
//        model.addAttribute("listOfActions", actionService.getActions());
        return "WorkUnitForm";
    }

    @PostMapping("/create")
    public String createWorkUnit(@Valid @ModelAttribute("WorkUnit") WorkUnitDto wdto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "WorkUnitForm";}

//        for (WorkUnitPart wup : wdto.getWorkUnitParts()) {
//            workUnitPartService.addWorkUnitPart(wu, wup.getId().getPartId());
//        }
//
//        for (WorkUnitAction wua : wdto.getWorkUnitActions()) {
//            workUnitActionService.addWorkUnitAction(wu, wua.getId().getActionId());
//        }

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
        model.addAttribute("listOfWorkUnitParts", workUnitPartService.getAllWorkUnitParts());
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

    // Delete Customer
    @GetMapping("/delete/{id}")
    public String deleteWorkUnit(@PathVariable("id") Long id, Model model) {
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        workUnitRepository.delete(workUnit);
        return "redirect:/workUnit/";
    }



/*    @GetMapping("/check-not-agreed")
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
    }*/


/*    @Controller
    @RequestMapping("/users")
    public class UserController {

        private final WorkUnitService workUnitService;

        public UserController(WorkUnitService workUnitService) {
            this.workUnitService = workUnitService;
        }*/

/*        @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
        public String updateWorkUnit(Model model) {
            List<WorkUnitDto> workUnits = workUnitService.getWorkUnits();
            model.addAttribute("listOfWorkUnits", workUnits);
            model.addAttribute("workUnit", new WorkUnit());
            return "WorkUnitUpdate";
        }

        @RequestMapping(value = "/test", method = RequestMethod.POST)
        public String updateWorkUnit(Model model, @ModelAttribute WorkUnitDto workUnitDto) {
            workUnitService.createWorkUnit(workUnitDto);
            return "redirect:/workUnit/";
        }*/


/*    @GetMapping("/update/parts/{id}")
    public String updateWorkUnitParts(@PathVariable("id") Long id, Model model) {
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("listOfCars", carService.getCars());
        model.addAttribute("listOfEmployees", employeeService.getEmployees());
        model.addAttribute("listOfWorkUnits", workUnitService.getWorkUnits());
        return "WorkUnitPartUpdate";
    }

    @PostMapping("/update/parts/{id}")
    public String updateWorkUnitParts(@PathVariable("id") Long id, @Valid WorkUnit workUnit,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            workUnit.setId(id);
            return "WorkUnitPartUpdate";
        }
        workUnitRepository.save(workUnit);
        return "redirect:/home";
    }*/

}
