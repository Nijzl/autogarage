package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.ActionDto;
import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.ActionRepository;
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
    private final ActionRepository actionRepository;

    protected ActionController(ActionService actionService, ActionRepository actionRepository){
        this.actionService = actionService;
        this.actionRepository = actionRepository;}


    // Get List of Actions
    @GetMapping("/")
    public String getActions(Model model) {
        var actions = actionService.getActions();
        model.addAttribute("listOfActions", actions);
        return "ActionsList";
    }

    // Get Single Action
    @GetMapping("/view/{id}")
    public String getAction(@PathVariable("id") Long id, Model model) {
        ActionDto action = actionService.getAction(id);
        model.addAttribute("action", action);
        return "ActionGet";
    }

    // Create New Action
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

    // Update Action
    @GetMapping("/update/{id}")
    public String updateAction(@PathVariable("id") Long id, Model model) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid action Id:" + id));
        model.addAttribute("action", action);
        return "ActionUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateAction(@PathVariable("id") Long id, @Valid Action action,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            action.setId(id);
            return "ActionUpdate";
        }
        actionRepository.save(action);
        return "redirect:/actions/";
    }

    // Delete Action
    @GetMapping("/delete/{id}")
    public String deleteAction(@PathVariable("id") Long id, Model model) {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid action Id:" + id));
        actionRepository.delete(action);
        return "redirect:/actions/";
    }

}
