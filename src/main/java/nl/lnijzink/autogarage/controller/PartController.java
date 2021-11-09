package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.service.PartService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartController {
    private final PartService service;

    protected PartController(PartService service){this.service = service;}

    @GetMapping("/{id}")
    public PartDto getPart(@PathVariable long id){
        return service.getPart(id);
    }

    @GetMapping("/create")
    public String createPart(Model model){
        model.addAttribute("Part", new PartDto());
        return "PartForm";
        }

    @PostMapping("/create")
    public String createPart(@Valid @ModelAttribute("Part") PartDto odto,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "PartForm";
        }
        service.createPart(odto);
        return "PartDisplay";
    }

    @PostMapping(path = "/parts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PartDto> getParts(){
        return service.getParts();
    }

}
