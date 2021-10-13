package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/part")
public class PartController {
    private final PartService service;

    protected PartController(PartService service){this.service = service;}

    @GetMapping("/part/id")
    public PartDto getPart(@PathVariable long id){
        return service.getPart(id);
    }

    @PostMapping("/part/create")
    @ResponseStatus(HttpStatus.CREATED)
    public long createPart(@RequestBody PartDto odto){return service.createPart(odto);}

    @GetMapping(path = "/parts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PartDto> getParts(){
        return service.getParts();
    }
}
