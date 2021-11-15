//package nl.lnijzink.autogarage.controller;
//
//import nl.lnijzink.autogarage.dto.WorkUnitDto;
//import nl.lnijzink.autogarage.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/workUnit")
//public class WorkUnitActionController {
//
//    @Autowired
//    WorkUnitService workunitService;
//    CarService carService;
//    EmployeeService employeeService;
//    PartService partService;
//    ActionService actionService;
//    WorkUnitPartService workUnitPartService;
//    WorkUnitActionService workUnitActionService;
//
//    protected WorkUnitActionController(WorkUnitService service, CarService carService, EmployeeService employeeService,
//                                       PartService partService, ActionService actionService,
//                                       WorkUnitActionService workUnitActionService,
//                                       WorkUnitPartService workUnitPartService){this.workunitService =
//            service; this.carService = carService; this.employeeService = employeeService; this.partService =
//            partService; this.actionService = actionService; this.workUnitPartService = workUnitPartService; this.workUnitActionService =
//            workUnitActionService;}
//
//    @GetMapping("/")
//    public String getWorkUnits(Model model) {
//        var workUnits = workunitService.getWorkUnits();
//        model.addAttribute("listOfWorkUnits", workUnits);
//        return "WorkUnitsList";
//    }
//
//    @GetMapping("/create")
//    public String createWorkUnit(Model model){
//        model.addAttribute("workUnit", new WorkUnitDto());
//        model.addAttribute("listOfCars", carService.getCars());
//        model.addAttribute("listOfEmployees", employeeService.getEmployees());
///*        model.addAttribute("listOfParts", partService.getParts());
//        model.addAttribute("listOfActions", actionService.getActions());*/
//        return "WorkUnitForm";
//    }
//
//    @PostMapping("/create")
//    public String createWorkUnit(@Valid @ModelAttribute("WorkUnit") WorkUnitDto wdto, BindingResult bindingResult){
////        if (bindingResult.hasErrors()) {
////            return "WorkUnitForm";}
//
//        var wu = workunitService.createWorkUnit(wdto);
//
////        for (WorkUnitPart wup : wdto.getWorkUnitParts()) {
////            workUnitPartService.addWorkUnitPart(wu, wup.getId().getPartId());
////        }
////
////        for (WorkUnitAction wua : wdto.getWorkUnitActions()) {
////            workUnitActionService.addWorkUnitAction(wu, wua.getId().getActionId());
////        }
//
//        return "WorkUnitDisplay";
//    }
//
//    @GetMapping("/check-not-agreed")
//    public String checkNotAgreed(){
//        return "WorkUnitQuintanceCheck";
//    }
//
//    @GetMapping("/repair")
//    public String repair(){
//        return "WorkUnitRepair";
//    }
//
//    @GetMapping("/quintance/check")
//    public String quintanceCheck(){
//        return "WorkUnitQuintanceCheck";
//    }
//
//    @GetMapping("/quintance/repair")
//    public String quintanceRepair(){
//        return "WorkUnitQuintanceRepair";
//    }
//
//
//
//}
