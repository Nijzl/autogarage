package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.WorkunitDto;
import nl.lnijzink.autogarage.reposit.WorkunitRepository;
import nl.lnijzink.autogarage.service.WorkunitService;
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
public class WorkunitController {

    @Autowired
    WorkunitRepository workunitRepository;
    WorkunitService workunitService;

    protected WorkunitController(WorkunitService service){this.workunitService = service;}

    @GetMapping("/create")
    public String createWorkunit(Model model){
        model.addAttribute("Workunit", new WorkunitDto());
        return "WorkunitForm";
    }

    @PostMapping("/create")
    public String createWorkunit(@Valid @ModelAttribute("Workunit") WorkunitDto wdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "WorkunitForm";
        }
        workunitService.createWorkunit(wdto);
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
