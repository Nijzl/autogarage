package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService service;

    protected CarController(CarService service){this.service = service;}

    @GetMapping("/id")
    public CarDto getCar(@PathVariable long id){
        return service.getCar(id);
    }

    @GetMapping("/create")
    public String createPart(Model model){
        model.addAttribute("Car", new CarDto());
        return "CarForm";
    }

    @PostMapping("/create")
    public String createCar(@Valid @ModelAttribute("Car") CarDto adto,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "CarForm";
        }
        service.createCar(adto);
        return "CarDisplay";
    }

    @GetMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarDto> getCars(){
        return service.getCars();
    }
}





/*
@RestController
public class CarController {
    @Autowired
    CarRepository carrepo;

    @GetMapping("/car")
    public String hello(){
        return "this is the car page";
    }
    @PostMapping("/add/car")
    public void addCustomer(@RequestParam String licencePlate){
        Car car1 = new Car();
        car1.setLicencePlate(licencePlate);
        carrepo.save(car1);

    }
}
*/