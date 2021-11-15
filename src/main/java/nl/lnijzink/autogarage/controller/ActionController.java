package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.ActionDto;
import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.service.ActionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/actions")
public class ActionController {

    private final ActionService actionService;

    protected ActionController(ActionService actionService){this.actionService = actionService;}

    @GetMapping("/")
    public String getActions(Model model) {
        var actions = actionService.getActions();
        model.addAttribute("listOfActions", actions);
        return "ActionsList";
    }

    @GetMapping("/{id}")
    public ActionDto getAction(@PathVariable Long id) {
        return actionService.getAction(id);
    }

    @GetMapping("/create")
    public String createAction(Model model) {
        model.addAttribute("Action", new ActionDto());
        return "ActionForm";
    }

    @PostMapping("/create")
    public String createAction(@Valid @ModelAttribute("Action") ActionDto actionDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ActionForm";
        }
        actionService.createAction(actionDto);
        return "ActionDisplay";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAction(@PathVariable("actionId") Long id){
        actionService.deleteAction(id);
        return "ActionDeleteDisplay";
    }

}
