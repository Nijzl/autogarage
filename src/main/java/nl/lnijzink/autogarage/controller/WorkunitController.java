package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.dto.WorkunitDto;
import nl.lnijzink.autogarage.model.Workunit;
import nl.lnijzink.autogarage.reposit.WorkunitRepository;
import nl.lnijzink.autogarage.service.CustomerService;
import nl.lnijzink.autogarage.service.WorkunitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/Workunit")
public class WorkunitController {
    @Autowired
    WorkunitRepository workunitRepository;

    protected WorkunitController(WorkunitService service){this.WorkunitService = service;}

    @PostMapping("/create")
    public String createWorkunit(@Valid @ModelAttribute("Workunit") WorkunitDto wdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "WorkunitForm";
        }
        WorkunitService.createWorkunit(wdto);
    }
}
