package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.reposit.PartRepository;
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

    private final PartService partService;
    private final PartRepository partRepository;

    protected PartController(PartService partService, PartRepository partRepository){
        this.partService = partService;
        this.partRepository = partRepository;}

    @GetMapping("/")
    public String getParts(Model model) {
        var parts = partService.getParts();
        model.addAttribute("listOfParts", parts);
        return "PartsList";
    }

    @GetMapping("/view/{id}")
    public String getPart(@PathVariable("id") Long id, Model model) {
        PartDto part = partService.getPart(id);
        model.addAttribute("part", part);
        return "PartGet";
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
            return "PartForm";}
        partService.createPart(odto);
        return "PartDisplay";
    }

    @GetMapping("/update/{id}")
    public String updatePart(@PathVariable("id") Long id, Model model) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part Id:" + id));
        model.addAttribute("part", part);
        return "PartUpdate";
    }

    @PostMapping("/update/{id}")
    public String updatePart(@PathVariable("id") Long id, @Valid Part part,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            part.setId(id);
            return "PartUpdate";
        }
        partRepository.save(part);
        return "redirect:/parts/";
    }

    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable("id") Long id, Model model) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part Id:" + id));
        partRepository.delete(part);
        return "redirect:/parts/";
    }

}
