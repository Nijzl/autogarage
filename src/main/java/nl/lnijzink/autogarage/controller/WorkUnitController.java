package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import nl.lnijzink.autogarage.service.WorkUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/workunit")
public class WorkUnitController {

    @Autowired
    WorkUnitRepository workunitRepository;
    WorkUnitService workunitService;

    protected WorkUnitController(WorkUnitService service){this.workunitService = service;}

    @GetMapping("/create")
    public String createWorkunit(Model model){
        model.addAttribute("Workunit", new WorkUnitDto());
        return "WorkunitForm";
    }

    @PostMapping("/create")
    public String createWorkunit(@Valid @ModelAttribute("Workunit") WorkUnitDto wdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "WorkunitForm";}
        workunitService.createWorkUnit(wdto);
        return "WorkunitForm";
    }

    @GetMapping("/checkup-not-agreed")
    public String checkupNotAgreed(){
        return "WorkunitCheckupNotAgreed";
    }

    @GetMapping("/repair")
    public String repair(){
        return "WorkunitRepair";
    }

    @GetMapping("/quintance")
    public String quintance(){
        return "WorkunitQuintance";
    }

}
