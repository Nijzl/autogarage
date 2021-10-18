package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.ActionDto;
import nl.lnijzink.autogarage.service.ActionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/action")
public class ActionController {
    private final ActionService service;

    protected ActionController(ActionService service){this.service = service;}

/*    @GetMapping("/part/{id}")
    public PartDto getPart(@PathVariable long id){
        return service.getPart(id);
    }

    @PostMapping("/part/create")
    @ResponseStatus(HttpStatus.CREATED)
    public long createPart(@RequestBody PartDto odto){return service.createPart(odto);}

    @GetMapping(path = "/parts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PartDto> getParts(){
        return service.getParts();
    }*/

    @GetMapping("/add")
    public String addAction(Model model){
        model.addAttribute("Action", new ActionDto());
        return "ActionForm";
    }
}
