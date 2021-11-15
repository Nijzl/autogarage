package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.reposit.PartRepository;
import nl.lnijzink.autogarage.service.ActionService;
import nl.lnijzink.autogarage.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final PartService partService;
    private final PartRepository partRepository;
    private final ActionService actionService;

    protected StockController(PartService partService, PartRepository partRepository, ActionService actionService){
        this.partService = partService;
        this.partRepository = partRepository;
        this.actionService = actionService;
    }

    @GetMapping("/")
    public String getStock(Model model) {
        var parts = partService.getParts();
        model.addAttribute("listOfParts", parts);
        var actions = actionService.getActions();
        model.addAttribute("listOfActions", actions);
        return "StockList";
    }

    @GetMapping("/update/{id}")
    public String updatePart(@PathVariable("id") Long id, Model model) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part Id:" + id));
        model.addAttribute("part", part);
        return "StockUpdate";
    }

    @PostMapping("/update/{id}")
    public String updatePart(@PathVariable("id") Long id, @Valid Part part,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            part.setId(id);
            return "StockUpdate";
        }
        partRepository.save(part);
        return "redirect:/stock/";
    }

    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable("id") Long id, Model model) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part Id:" + id));
        partRepository.delete(part);
        return "redirect:/stock/";
    }


}
